package com.ea.emiratesauction.core.realTimeManager.event

import com.ea.emiratesauction.core.constants.realTimeCommunication.events.RTCEvent
import com.ea.emiratesauction.core.realTimeManager.channel.RTCChannelInterface

/**
 * The event interface which is used by the RTC manager and providers under the RTCChannels
 */
interface RTCEventInterface {
    /**
     * The name of the event
     */
    var name: RTCEvent

    /**
     * The event data to be emitted or used with the event - in order to push to the broadcaster
     */
    var data: Map<String, Any>

    /**
     * The channel info which the event is associated with
     */
    var channel: RTCChannelInterface
}