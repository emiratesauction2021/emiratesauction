package com.ea.emiratesauction.core.realTimeManager.manager


import com.ea.emiratesauction.core.realTimeManager.data.RTMChannel
import com.ea.emiratesauction.core.realTimeManager.data.RTMEvent
import com.ea.emiratesauction.core.realTimeManager.interfaces.RTMProviderInterface
import com.ea.emiratesauction.core.realTimeManager.interfaces.RTMManagerInterface
import com.ea.emiratesauction.core.realTimeManager.interfaces.RTMProviderCallBack
import java.util.*
import javax.inject.Inject
import kotlin.collections.ArrayList
import kotlin.collections.HashMap

class RTMManager @Inject constructor(val provider: RTMProviderInterface) : RTMManagerInterface,
    RTMProviderCallBack {

    init {
        provider.responseListener = this
    }

    private val currentEvents = ArrayList<RTMEvent>()
    private val eventListeners = HashMap<Pair<String, String>, ArrayList<(Any) -> Unit>>()


    override fun listen(event: RTMEvent, dataCallBack: (Any) -> Unit) {

        if (currentEvents.contains(event)) {
            eventListeners.get(Pair(event.name, event.channelData.name.value))!! += dataCallBack

        } else {

            val eventList = ArrayList<(Any) -> Unit>()

            eventList += dataCallBack
            eventListeners[Pair(event.name, event.channelData.name.value)] = eventList

            provider.listen(event)

        }

    }

    override fun initialize() {
        provider.initialize()
    }

    override fun connect() {
        provider.connect()
    }

    override fun subscribe(channel: RTMChannel) {
        provider.subscribe(channel)
    }


    override fun disconnect() {
        provider.disconnect()
    }


    override fun unSubscribe(channel: RTMChannel) {
       provider.unSubscribe(channel)
    }

    override fun stopListen(event: RTMEvent) {
       provider.stopListen(event)
    }


    override fun onDataReceived(channelName: String, eventName: String, response: Any) {
        val key = Pair(eventName, channelName)
        val listeners = eventListeners.get(key)
        listeners?.let {
            for (i in it) {
                i(response)
            }
        }
    }


}






