package com.ea.emiratesauction.core.realTimeManager.manager


import android.annotation.SuppressLint
import android.util.Log
import com.ea.emiratesauction.core.realTimeManager.data.RTMChannel
import com.ea.emiratesauction.core.realTimeManager.data.RTMEvent
import com.ea.emiratesauction.core.realTimeManager.interfaces.RTMManagerCallBack
import com.ea.emiratesauction.core.realTimeManager.interfaces.RTMProviderInterface
import com.ea.emiratesauction.core.realTimeManager.interfaces.RTMManagerInterface
import com.ea.emiratesauction.core.realTimeManager.interfaces.RTMProviderCallBack
import java.lang.Exception
import javax.inject.Inject
import kotlin.collections.ArrayList
import kotlin.collections.HashMap

/**
 * R t m manager
 *
 * @property provider
 * @constructor Create empty R t m manager
 */
class RTMManager @Inject constructor(val provider: RTMProviderInterface) : RTMManagerInterface,
    RTMProviderCallBack {
    val TAG = "RTMManager"

    init {
        provider.responseListener = this
    }

    /**
     * Channels events
     */
    private val channelsEvents =
        HashMap<RTMChannel, ArrayList<String>>()//array list contains channel's events

    /**
     * Event listeners
     */
    private val eventListeners =
        HashMap<Pair<String, String>, ArrayList<RTMManagerCallBack>>()//first is event name, second is channel name


    override fun initialize() {
        if (!provider.isInitialized)
            provider.initialize()
    }

    override fun connect() {
        if (!provider.isConnected)
            provider.connect()
    }

    /**
     * Subscribe
     *
     * @param channel
     */
    override fun subscribe(channel: RTMChannel) {
        if (!channelsEvents.containsKey(channel)) {
            channelsEvents[channel] = ArrayList()
            provider.subscribe(channel)
        } else {
            Log.w(TAG, "channel with name ${channel.name} is already connected")
        }
    }

    /**
     * Listen
     *
     * @param event
     * @param dataCallBack
     */
    override fun listen(event: RTMEvent, dataCallBack: RTMManagerCallBack) {

        if (eventListeners.contains(decodeKey(event))) {

            eventListeners.get(decodeKey(event))!! += dataCallBack

        } else {

            val eventList = ArrayList<RTMManagerCallBack>()

            eventList += dataCallBack
            eventListeners[decodeKey(event)] = eventList

            provider.listen(event)

        }

    }

    /**
     * Check channel events
     *
     * @param channel
     */
    private fun checkChannelEvents(channel: RTMChannel) {
        val events = channelsEvents.get(channel)
        if (events == null || events.size <= 0) {
            unSubscribe(channel)
            channelsEvents.remove(channel)
        }
    }

    private fun checkEventListeners(event: RTMEvent) {
        val listeners = eventListeners.get(decodeKey(event))
        if (listeners == null || listeners.size <= 0) {
            eventListeners.remove(decodeKey(event))
            checkChannelEvents(event.channelData)
        }
    }

    /**
     * Stop listen
     *
     * @param event
     * @param callbackObject
     */
    override fun stopListen(event: RTMEvent, callbackObject: RTMManagerCallBack) {
        if (eventListeners.containsKey(decodeKey(event))) {
            eventListeners.get(decodeKey(event))?.remove(callbackObject)
            checkEventListeners(event)
        } else {
            throw Exception("event is not added before")
        }
    }

    /**
     * Stop listen
     *
     * @param event
     */

    override fun stopListen(event: RTMEvent) {
        if (eventListeners.containsKey(decodeKey(event))) {
            provider.stopListen(event)

        } else {
            throw Exception("event is not added before")
        }
    }

    /**
     * Un subscribe
     *
     * @param channel
     */
    override fun unSubscribe(channel: RTMChannel) {
        val events = channelsEvents.get(channel)
        if (events != null) {
            for (event in events) {
                eventListeners.remove(decodeKey(null, event, channel.name.value))
            }
        }
    }

    /**
     * Disconnect
     *
     */
    //we clear all channels and events
    override fun disconnect() {
        channelsEvents.clear()
        eventListeners.clear()
        provider.disconnect()
    }

    /**
     * On data received
     *
     * @param channelName
     * @param eventName
     * @param response
     */
    // here we receive data from provider
    override fun onDataReceived(channelName: String, eventName: String, response: Any) {
        val key = decodeKey(null, eventName, channelName)
        val listeners = eventListeners.get(key)
        listeners?.let {
            for (i in it) {
                i.onDataReceived(response)
            }
        }
    }

    /**
     * Decode key
     *
     * @param event
     * @param eventName
     * @param channelName
     * @return
     */
    // this function is to keep logic in decode event or -event name and channel name- is one across manager
    private fun decodeKey(
        event: RTMEvent?,
        eventName: String = "",
        channelName: String = ""
    ): Pair<String, String> {
        if (event != null)
            return Pair(event.name, event.channelData.name.value)
        else {
            return Pair(eventName, channelName)

        }
    }

}






