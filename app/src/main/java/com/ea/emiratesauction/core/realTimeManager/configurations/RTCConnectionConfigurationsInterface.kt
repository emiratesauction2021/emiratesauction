package com.ea.emiratesauction.core.realTimeManager.configurations

/**
 * The configurations which are used by the provider to initialize
 */
interface RTCConnectionConfigurationsInterface {
    /**
     * The URL which is used by the provider for the RTC
     */
    var url: String

    /**
     * The port which is used by the provider for the RTC
     */
    var port: Int

    /**
     * The authentications which are required by the provider to start the broadcasting
     */
    var authentication: Map<String, Any>
}