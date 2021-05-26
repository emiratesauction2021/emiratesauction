package com.ea.emiratesauction.core.analytics.event

import com.ea.emiratesauction.core.analytics.providers.AnalyticsProvider

interface AnalyticsEventInterface {
    var eventName: String
    var params: Map<String, Any>
    var providers: ArrayList<AnalyticsProvider>
}


