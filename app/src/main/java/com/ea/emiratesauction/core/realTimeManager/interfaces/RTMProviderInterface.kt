package com.ea.emiratesauction.core.realTimeManager.interfaces

import com.ea.emiratesauction.core.realTimeManager.data.RTMEvent

/**
 * @property isInitialized is get boolean to check if provider is Initialized will need to provide private variable of type boolean to set and get
 * @property isConnected is get boolean to check if provider is connected or not will need to provide private variable of type boolean to set and get
 * @property responseListener is called to return data after received it
 * @property listen is to start listen to event
 * @property stopListen is to stop listen to event
 */

interface RTMProviderInterface:RTMBaseInterface  {

    val isInitialized:Boolean
    val isConnected:Boolean
    var responseListener:RTMProviderCallBack?
    fun listen(event: RTMEvent)
    fun stopListen(event: RTMEvent)

}