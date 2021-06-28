package com.ea.emiratesauction.core.realTimeManager.manager.interfaces

import com.ea.emiratesauction.core.realTimeManager.event.RTCEventInterface
import com.ea.emiratesauction.core.realTimeManager.providers.interfaces.RTCProviderInterface

/**
 * The interface which any real time manager should implement to be part of the application RTMs
 */
interface RTMManagerInterface {

    /**
     * Starts the connection with the provider
     *
     * @throws Exception if the provider isn't been initialized yet - call setRTCClient first
     */
    fun connect()

    /**
     * Stops the connection with the provider
     *
     * @throws Exception if the provider isn't been initialized yet - call setRTCClient first
     */
    fun disconnect()


    /**
     * Sets the used client within the provider
     *
     * @param rtcProvider The provider which the manager will use to start doing the RTC
     *
     * @throws Exception If the client is already has been set before
     */
    fun setRTCClient(rtcProvider: RTCProviderInterface)

    /**
     * Add new listener for a specific event
     *
     * @param listener The listener to be subscribed
     *
     * @param event The event which the listener will subscribe to
     *
     * @throws Exception if the provider isn't been initialized yet - call setRTCClient first
     */
    fun addListener(listener: RTMManagerCallBack, event: RTCEventInterface)

    /**
     * Remove an existing listener
     *
     * @param listener The listener to be unsubscribed
     *
     * @param event The event which the listener will unsubscribe from
     *
     * @throws Exception if the provider isn't been initialized yet - call setRTCClient first
     */
    fun removeListener(listener: RTMManagerCallBack, event: RTCEventInterface)

    /**
     * Emit event data to the provider
     *
     * @param event The event to be emitted
     *
     * @throws Exception if the provider isn't been initialized yet - call setRTCClient first
     */
    fun emit(event: RTCEventInterface)
}