package com.ea.emiratesauction.core.analytics.profile.identification

import com.ea.emiratesauction.core.constants.analytics.AnalyticsProviderIdentifier

/**
 * Base Analytics Profile identification
 *
 * @constructor Create empty Profile identification interface
 */
abstract class BaseAnalyticsProfileIdentification {
    abstract var profileID: String
    open var providerIdentifiers: List<AnalyticsProviderIdentifier> = AnalyticsProviderIdentifier.values().toCollection(ArrayList())
}