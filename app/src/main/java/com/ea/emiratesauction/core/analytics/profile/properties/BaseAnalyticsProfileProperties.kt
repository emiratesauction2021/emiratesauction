package com.ea.emiratesauction.core.analytics.profile.properties

import com.ea.emiratesauction.core.analytics.managers.interfaces.AnalyticsProperties
import com.ea.emiratesauction.core.constants.analytics.providers.AnalyticsProviderIdentifier

/**
 * The abstract class which contains all the required parameters and properties to be linked with the user profile in an analytics provider
 */
abstract class BaseAnalyticsProfileProperties {
    /**
     * The properties of a user to be linked with his profile
     */
    abstract var properties: AnalyticsProperties

    /**
     * The provider identifiers which that event is supporting, by default it contains all the identifiers
     *
     * Note: Only update it if you want specific providers to fire this event
     *
     * @see AnalyticsProviderIdentifier
     */
    open var providerIdentifiers: List<AnalyticsProviderIdentifier> = AnalyticsProviderIdentifier.values().toCollection(ArrayList())
}