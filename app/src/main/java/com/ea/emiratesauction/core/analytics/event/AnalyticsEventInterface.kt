package com.ea.emiratesauction.core.analytics.event

import com.ea.emiratesauction.core.analytics.providers.AnalyticsProviderIdentifier

/**
 * Analytics event interface
 *
 * @constructor Create empty Analytics event interface
 */
interface AnalyticsEventInterface {
    var name: String
    var properties: Map<String, Any>
    var providerIdentifiers: ArrayList<AnalyticsProviderIdentifier>
}


