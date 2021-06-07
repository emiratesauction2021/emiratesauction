package com.ea.emiratesauction.core.analytics.profile.identification

import com.ea.emiratesauction.core.constants.analytics.AnalyticsProviderIdentifier

/**
 * The abstract class which contains all the required parameters and properties for identifying the user in an analytics provider
 */
abstract class BaseAnalyticsProfileIdentification {
    /**
     * The ID which will be used to identify the user in the analytics provider
     */
    abstract var profileID: String

    /**
     * The provider identifiers which that event is supporting, by default it contains all the identifiers
     *
     * Note: Only update it if you want specific providers to fire this event
     */
    open var providerIdentifiers: List<AnalyticsProviderIdentifier> = AnalyticsProviderIdentifier.values().toCollection(ArrayList())
}
