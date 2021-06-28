package com.ea.emiratesauction.core.realTimeManager.keys

import com.ea.emiratesauction.core.constants.realTimeCommunication.channels.RTCChannel
import com.ea.emiratesauction.core.constants.realTimeCommunication.events.RTCEvent
import java.util.*

/**
 * The complex key which is used to distinguish the events and channels - used mainly by the RTCManager
 *
 * @property eventName The event name
 *
 * @property channelName The channel name
 */
class RTCComplexKey (val eventName: RTCEvent, val channelName: RTCChannel){

    override fun equals(other: Any?): Boolean {
        /** Overriding the equal to make sure that RTCComplexKey uses the event name and the channel name in the comparison */
        if (this === other) return true
        if (other?.javaClass != javaClass) return false

        other as RTCComplexKey

        return this.eventName == other.eventName && this.channelName == other.channelName
    }

    override fun hashCode(): Int {
        /** Overriding the hash to make sure that RTCComplexKey uses the event name and the channel name in the hashing */
        return Objects.hash(eventName, channelName)
    }
}
