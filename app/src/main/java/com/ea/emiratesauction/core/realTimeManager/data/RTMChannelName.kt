package com.ea.emiratesauction.core.realTimeManager.data

/**
 * if there is new channel need to subscribe with it first need to add channel name here
 * @see DRIVE_STATUS as an example
 */
enum class RTMChannelName(val value:String) {
    DRIVE_STATUS("GetDriveStatus")
}