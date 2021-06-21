package com.ea.emiratesauction.core.analytics.event

import com.ea.emiratesauction.core.analytics.managers.interfaces.AnalyticsProperties
import com.ea.emiratesauction.core.constants.analytics.providers.AnalyticsProviderIdentifier

/**
 * The base analytics event which is used by the analytics manager through out the whole app, it contains the different required information for an analytics event
 * such as event name, properties ...etc
 */
abstract class BaseAnalyticsEvent {
    /**
     * The event name
     */
    abstract var name: String

    /**
     * The properties to be linked with an event
     */
    abstract var properties: AnalyticsProperties

    /**
     * The provider identifiers which that event is supporting, by default it contains all the identifiers
     *
     * Note: Only update it if you want specific providers to fire this event
     *
     * @see AnalyticsProviderIdentifier
     */
    open var providerIdentifiers: ArrayList<AnalyticsProviderIdentifier> = AnalyticsProviderIdentifier.values().toCollection(ArrayList())
}