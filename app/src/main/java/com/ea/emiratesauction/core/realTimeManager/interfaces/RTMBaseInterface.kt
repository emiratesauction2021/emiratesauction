package com.ea.emiratesauction.core.realTimeManager.interfaces

import androidx.lifecycle.MutableLiveData
import com.ea.emiratesauction.core.realTimeManager.data.RTMChannel
import com.ea.emiratesauction.core.realTimeManager.data.RTMEvent

/**
 * @sample RTMBaseInterface
 * is contain common functions between Manager and Provider
 * @property initialize is for make provider initializations
 * @property connect after make init is start to connect
 * @property subscribe is to connect -subscribe- to channel that passed
 *@property unSubscribe to close connect with channel
 */
interface RTMBaseInterface {
    fun initialize()
    fun connect()
    fun disconnect()

    /**
     * @param channel is the channel data to connect with it
     */
    fun subscribe(channel: RTMChannel)
    fun unSubscribe(channel: RTMChannel)

}