package com.ea.emiratesauction.core.analytics.defaultProperties.event

import com.ea.emiratesauction.core.analytics.managers.interfaces.AnalyticsProperties

/**
 * The abstract class which contains all the default parameters and properties for an analytics event
 */

abstract class BaseAnalyticsDefaultEventProperties {
    /**
     * The default properties of an Event
     */
    abstract var properties: AnalyticsProperties
}