package com.ea.emiratesauction.core.analytics.screen

import com.ea.emiratesauction.core.analytics.providers.AnalyticsProviderIdentifier

interface AnalyticsScreenInterface {

    var name: String
    var attributes: ArrayList<Pair<String,Any>>
    var providerIdentifiers: ArrayList<AnalyticsProviderIdentifier>
}