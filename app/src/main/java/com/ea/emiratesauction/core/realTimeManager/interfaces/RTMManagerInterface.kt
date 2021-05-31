package com.ea.emiratesauction.core.realTimeManager.interfaces

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Observer
import com.ea.emiratesauction.core.realTimeManager.data.RTMChannel
import com.ea.emiratesauction.core.realTimeManager.data.RTMEvent

interface RTMManagerInterface:RTMBaseInterface {
    fun listen(event: RTMEvent,dataCallBack:(Any)->Unit)

}