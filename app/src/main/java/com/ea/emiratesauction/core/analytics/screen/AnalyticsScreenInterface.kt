package com.ea.emiratesauction.core.analytics.screen

import com.ea.emiratesauction.core.analytics.providers.AnalyticsProvider

interface AnalyticsScreenInterface {

    var eventName: String
    var params: Map<String, Any>
    var providers: ArrayList<AnalyticsProvider>
}