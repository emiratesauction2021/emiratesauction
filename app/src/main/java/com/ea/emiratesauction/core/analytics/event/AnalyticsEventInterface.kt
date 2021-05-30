package com.ea.emiratesauction.core.analytics.event

import com.ea.emiratesauction.core.analytics.providers.AnalyticsProviderIdentifier

/*

 */
interface AnalyticsEventInterface {
    var name: String
    var properties: Map<String, Any>
    var providerIdentifiers: ArrayList<AnalyticsProviderIdentifier>
}


