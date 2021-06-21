package com.ea.emiratesauction.core.realTimeManager.channel

import com.ea.emiratesauction.core.constants.realTimeCommunication.channels.RTCChannel

/**
 * The channel interface which is used by the RTC manager and providers
 */
interface RTCChannelInterface {
    /**
     * The name of the channel which contains multiple events
     */
    var name: RTCChannel
}