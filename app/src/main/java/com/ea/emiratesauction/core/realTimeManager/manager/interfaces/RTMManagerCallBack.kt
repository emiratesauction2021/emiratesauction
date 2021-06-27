package com.ea.emiratesauction.core.realTimeManager.manager.interfaces

import com.ea.emiratesauction.core.realTimeManager.keys.RTCComplexKey

/**
 * The callback methods which the manager will use to send back the results or the errors to the listeners
 */
interface RTMManagerCallBack {
    /**
     * The manager did receive data from the RTC provider for a specific event under a channel
     *
     * @param response The raw data which is received from the provider
     *
     * @param key The complex key which is combining the channel name and event name
     */
    fun didReceiveResult(response:Any, key: RTCComplexKey)

    /**
     * The manager did receive an error from the RTC provider for a specific event under a channel
     *
     * @param error The error's raw data which is received from the provider
     *
     * @param key The complex key which is combining the channel name and event name
     */
    fun didReceiveError(error:Any, key: RTCComplexKey)
}