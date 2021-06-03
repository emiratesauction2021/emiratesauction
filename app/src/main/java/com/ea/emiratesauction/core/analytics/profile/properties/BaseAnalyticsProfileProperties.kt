package com.ea.emiratesauction.core.analytics.profile.properties

import com.ea.emiratesauction.core.analytics.managers.interfaces.AnalyticsProperties
import com.ea.emiratesauction.core.constants.analytics.AnalyticsProviderIdentifier

/**
 * Base Analytics Profile Properties
 *
 * @constructor Create empty Profile properties interface
 */
abstract class BaseAnalyticsProfileProperties {
    abstract var properties: AnalyticsProperties
    open var providerIdentifiers: List<AnalyticsProviderIdentifier> = AnalyticsProviderIdentifier.values().toCollection(ArrayList())
}