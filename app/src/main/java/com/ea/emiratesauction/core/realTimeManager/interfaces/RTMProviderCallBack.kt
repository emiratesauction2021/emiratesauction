package com.ea.emiratesauction.core.realTimeManager.interfaces

interface RTMProviderCallBack {
    fun onDataReceived(channelName:String,eventName:String,response:Any)
}