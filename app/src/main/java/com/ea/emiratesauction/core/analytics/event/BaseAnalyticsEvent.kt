package com.ea.emiratesauction.core.analytics.event

import com.ea.emiratesauction.core.analytics.managers.interfaces.AnalyticsProperties
import com.ea.emiratesauction.core.constants.analytics.AnalyticsProviderIdentifier

/**
 * Base Analytics Event
 *
 * @constructor Create empty Analytics event interface
 */
abstract class BaseAnalyticsEvent {
    abstract var name: String
    abstract var properties: AnalyticsProperties
    open var providerIdentifiers: ArrayList<AnalyticsProviderIdentifier> = AnalyticsProviderIdentifier.values().toCollection(ArrayList())
}


