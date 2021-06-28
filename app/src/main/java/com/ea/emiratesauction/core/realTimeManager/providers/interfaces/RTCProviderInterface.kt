package com.ea.emiratesauction.core.realTimeManager.providers.interfaces

import com.ea.emiratesauction.core.realTimeManager.channel.RTCChannelInterface
import com.ea.emiratesauction.core.realTimeManager.configurations.RTCConnectionConfigurationsInterface
import com.ea.emiratesauction.core.realTimeManager.event.RTCEventInterface

/**
 * The interface which any real time provider should implement to be part of the application RTPs
 */
abstract class RTCProviderInterface(var listener: RTCProviderCallBack, var connectionConfiguration: RTCConnectionConfigurationsInterface)  {
    /**
     * Starts the connection
     */
    abstract fun connect()

    /**
     * Subscribes to a channel
     *
     * @param channel The channel name and its details
     */
    abstract fun subscribeToChannel(channel: RTCChannelInterface)

    /**
     * Unsubscribe from a channel
     *
     * @param channel The channel name and its details
     */
    abstract fun unsubscribeFromChannel(channel: RTCChannelInterface)

    /**
     * Sends data to the server
     *
     * @param event The event name and its data
     */
    abstract fun emit(event: RTCEventInterface)

    /**
     * Starts listening on a specific event
     *
     * @param event The event name and its data
     */
    abstract fun startListenToEvent( event: RTCEventInterface)

    /**
     * Stops listening on a specific event
     *
     * @param event The event name and its data
     */
    abstract fun stopListenToEvent(event: RTCEventInterface)

    /**
     * Stops the connection
     */
    abstract fun disconnect()
}