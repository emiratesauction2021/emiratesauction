package com.ea.emiratesauction.core.realTimeManager.interfaces

/**
 * return data to caller -helper, UI, ..etc-
 */
interface RTMManagerCallBack {
    /**
     * @param response is data returned from provider
     */
    fun onDataReceived(response:Any)

}