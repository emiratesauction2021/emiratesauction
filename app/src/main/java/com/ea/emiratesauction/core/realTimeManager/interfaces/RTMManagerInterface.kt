package com.ea.emiratesauction.core.realTimeManager.interfaces

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Observer
import com.ea.emiratesauction.core.realTimeManager.data.RTMChannel
import com.ea.emiratesauction.core.realTimeManager.data.RTMEvent

/**
 * @property listen is to start listen of event it need
 * @property stopListen is to end connect with with event
 */
interface RTMManagerInterface:RTMBaseInterface {
    /**
     * @param event is event data
     * @param dataCallBack is for return data to caller after return from provider
     */
    fun listen(event: RTMEvent,dataCallBack:RTMManagerCallBack)

    /**
     * end connection with event and remove all listeners
     */
    fun stopListen(event: RTMEvent)

    /**
     * remove specific listener which pass in
     * @param callbackObject
     */
    fun stopListen(event: RTMEvent,callbackObject:RTMManagerCallBack)

}