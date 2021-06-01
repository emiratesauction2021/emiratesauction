package com.ea.emiratesauction.core.realTimeManager.interfaces

/**
 * return data from provider to manager
 *
 */
interface RTMProviderCallBack {
    /**
     * @param channelName is name of channel should return with data received in provider
     * @param eventName is name of event should return with data received in provider
     * @param response is data received in provider
     */
    fun onDataReceived(channelName:String,eventName:String,response:Any)

}