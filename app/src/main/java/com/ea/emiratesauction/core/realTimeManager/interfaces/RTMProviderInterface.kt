package com.ea.emiratesauction.core.realTimeManager.interfaces

import com.ea.emiratesauction.core.realTimeManager.data.RTMChannel
import com.ea.emiratesauction.core.realTimeManager.data.RTMEvent

interface RTMProviderInterface:RTMBaseInterface  {
    var responseListener:RTMProviderCallBack?
    fun listen(event: RTMEvent)

}