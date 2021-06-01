package com.ea.emiratesauction.core.realTimeManager.data

/**
 * this use for socket channel data
 * @property name it should be name of type Enum, see
 * @property RTMChannelName
 *
 */
class RTMChannel {
    lateinit var name:RTMChannelName
    lateinit var port:String
}