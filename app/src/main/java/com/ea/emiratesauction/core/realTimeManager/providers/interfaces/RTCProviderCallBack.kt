package com.ea.emiratesauction.core.realTimeManager.providers.interfaces

/**
 * The callback methods which the provider will use to send back the results or the errors to the manager
 */
interface RTCProviderCallBack {
    /**
     * The provider did receive data from the server
     *
     * @param result The raw data which is received by the provider
     *
     * @param channelName The channel name which received the data
     *
     * @param eventName The event name which received the data
     */
    fun onDataReceived(channelName:String, eventName:String, result:Any)

    /**
     * The provider did receive an error from the server
     *
     * @param error The error data which is received by the provider
     *
     * @param channelName The channel name which received the error
     *
     * @param eventName The event name which received the error
     */
    fun onErrorReceived(channelName:String, eventName:String, error:Any)

}