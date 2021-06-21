package com.ea.emiratesauction.core.realTimeManager.manager


import android.util.Log
import com.ea.emiratesauction.core.constants.realTimeCommunication.channels.RTCChannel
import com.ea.emiratesauction.core.constants.realTimeCommunication.events.RTCEvent
import com.ea.emiratesauction.core.extensions.asEnum
import com.ea.emiratesauction.core.realTimeManager.event.RTCEventInterface
import com.ea.emiratesauction.core.realTimeManager.manager.interfaces.RTMManagerCallBack
import com.ea.emiratesauction.core.realTimeManager.providers.interfaces.RTCProviderInterface
import com.ea.emiratesauction.core.realTimeManager.manager.interfaces.RTMManagerInterface
import com.ea.emiratesauction.core.realTimeManager.keys.RTCComplexKey
import com.ea.emiratesauction.core.realTimeManager.providers.interfaces.RTCProviderCallBack
import java.lang.Exception
import kotlin.collections.ArrayList
import kotlin.collections.HashMap

/**
 * The singleton manager which is responsible for any RTC using a specific provider
 *
 * It's the separation layer between the app and any RTC provider
 */
object RTCManager: RTMManagerInterface, RTCProviderCallBack {

    /** The internal and private identifiers used by the manager */
    private var rtcListeners = HashMap<RTCComplexKey, ArrayList<RTMManagerCallBack>>()
    private lateinit var rtcProvider: RTCProviderInterface
    private var isConnected = false
    private const val tag = "RTCManager"
    /*************************************************************/

    override fun connect() {
        // Check if the manager is already connected to the broadcaster or not
        if(!isConnected){
            this.checkClientInitialization()
            rtcProvider.connect()
            isConnected = true
        }else {
            Log.w(tag, "Manager is already connected")
        }
    }

    override fun disconnect() {
        // Check if the manager is already connected to the broadcaster or not
        if(isConnected){
            this.checkClientInitialization()
            rtcProvider.disconnect()
            isConnected = false
        }else {
            Log.w(tag, "Manager is already disconnected")
        }
    }

    override fun setRTCClient(rtcProvider: RTCProviderInterface) {
        // Check if the RTC is already initialized before or not
        checkClientInitialization(true)
        // Initialize the internal instance of the provider
        this.rtcProvider = rtcProvider
    }

    override fun addListener(listener: RTMManagerCallBack, event: RTCEventInterface) {
        // Check if the RTC is already initialized before or not
        checkClientInitialization()

        // Creating the complex key using the data of the event
        val tempKey = RTCComplexKey(event.name, event.channel.name)

        // Trying to find a key which matches the complex key
        val key = rtcListeners.filter { it.key == tempKey }.keys.firstOrNull()

        // If the key isn't found, then the channel isn't created previously
        if(key == null){
            // Create the channel and subscribe to it with the provider
            rtcListeners[tempKey] = arrayListOf()
            rtcProvider.subscribeToChannel(event.channel)
            rtcProvider.startListenToEvent(event)
        }

        key?.let {
            // Add the listener
            rtcListeners[it]?.add(listener)
        }

    }

    override fun removeListener(listener: RTMManagerCallBack, event: RTCEventInterface) {
        // Check if the RTC is already initialized before or not
        checkClientInitialization()

        // Creating the complex key using the data of the event
        val tempKey = RTCComplexKey(event.name, event.channel.name)

        // Trying to find a key which matches the complex key
        val key = rtcListeners.filter { it.key == tempKey }.keys.firstOrNull()

        // If the key isn't found then the listener isn't found
        if(rtcListeners[key].isNullOrEmpty()){
            Log.w(tag, "This listener for the event ${event.name.value} isn't found")
        }else {
            // Remove the listener from the listeners associated with the key
            rtcListeners[key]?.let {
                it.remove(listener)
            }
        }

        // If the listeners of a specific keys reaches zero, then stop listening to the event
        if(rtcListeners[key].isNullOrEmpty()) {
            rtcListeners.remove(key)
            rtcProvider.stopListenToEvent(event)
        }

        // Check if the channel isn't used by any other keys
        val channelKey =  rtcListeners.filter { it.key.channelName == key?.channelName }.keys
        // If the channel isn't used anymore - then the app unsubscribe from it
        if(channelKey.isNullOrEmpty()){
            rtcProvider.unsubscribeFromChannel(event.channel)
        }
    }

    override fun emit(event: RTCEventInterface) {
        // Check if the RTC is already initialized before or not
        checkClientInitialization()

        // Emits the event
        rtcProvider.emit(event)
    }

    /**
     * Checks if the client is initialized or not
     *
     * @param throwIfInitialized If it's true then it will throw an exception if the client is initialized otherwise it will throw if it's not initialized
     *
     * @throws Exception if the client is initialized and throwIfInitialized is true otherwise it will throw if it's not initialized
     */
    private fun checkClientInitialization(throwIfInitialized: Boolean = false) {
        if(!this::rtcProvider.isInitialized && !throwIfInitialized){
            throw Exception("RTC Client isn't initialized yet - Please call setRTCClient method before using the RTCManager")
        }else if(this::rtcProvider.isInitialized && throwIfInitialized) {
            throw Exception("RTC Client is already initialized - You can't call setRTCClient method twice")
        }
    }

    /** The RTC provider callbacks */
    override fun onDataReceived(channelName: String, eventName: String, result: Any) {
        // Creating the complex key using the data of the event

        val tempKey = RTCComplexKey(eventName.asEnum(RTCEvent.UNKNOWN), eventName.asEnum(RTCChannel.UNKNOWN))

        // Trying to find a key which matches the complex key
        val key = rtcListeners.filter { it.key == tempKey }.keys.firstOrNull()

        // Passing the data back to the listeners
        key?.let { key ->
            rtcListeners[key]?.let {
                it.forEach { callback ->
                    callback.didReceiveResult(result, key)
                }
            }
        }
    }

    override fun onErrorReceived(channelName: String, eventName: String, error: Any) {
        // Creating the complex key using the data of the event
        val tempKey = RTCComplexKey(eventName.asEnum(RTCEvent.UNKNOWN), eventName.asEnum(RTCChannel.UNKNOWN))

        // Trying to find a key which matches the complex key
        val key = rtcListeners.filter { it.key == tempKey }.keys.firstOrNull()

        // Passing the error back to the listeners
        key?.let { key ->
            rtcListeners[key]?.let {
                it.forEach { callback ->
                    callback.didReceiveError(error, key)
                }
            }
        }
    }
    /**************************************/
}