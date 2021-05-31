package com.ea.emiratesauction.core.realTimeManager.interfaces

import androidx.lifecycle.MutableLiveData
import com.ea.emiratesauction.core.realTimeManager.data.RTMChannel
import com.ea.emiratesauction.core.realTimeManager.data.RTMEvent

interface RTMBaseInterface {
    fun initialize()
    fun connect()
    fun disconnect()
    fun subscribe(channel: RTMChannel)
    fun unSubscribe(channel: RTMChannel)
    fun stopListen(event: RTMEvent)

}