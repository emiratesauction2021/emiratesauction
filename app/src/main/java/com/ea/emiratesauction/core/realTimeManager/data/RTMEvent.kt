package com.ea.emiratesauction.core.realTimeManager.data

/**
 * mostly channel contain events so we need to declare event with channel owner
 * @property channelData is for channel owner data
 * @property name is for event itself name
 */
class RTMEvent {
    lateinit var channelData: RTMChannel
    lateinit var name:String
}