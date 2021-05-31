package com.ea.emiratesauction.core.analytics.screen

import com.ea.emiratesauction.core.analytics.providers.AnalyticsProviderIdentifier

/*
define screen event which contain screen name and attributes
provided with analytics provider
 */
interface AnalyticsScreenInterface {

    var name: String
    var attributes: ArrayList<Pair<String, Any>>
    var providerIdentifiers: ArrayList<AnalyticsProviderIdentifier>
}