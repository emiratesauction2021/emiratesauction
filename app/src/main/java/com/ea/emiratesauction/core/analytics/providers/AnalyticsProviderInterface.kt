package com.ea.emiratesauction.core.analytics.providers

import com.ea.emiratesauction.core.analytics.UserIdentification
import com.ea.emiratesauction.core.analytics.event.AnalyticsEventInterface
import com.ea.emiratesauction.core.analytics.screen.AnalyticsScreenInterface

interface AnalyticsProviderInterface {
    var provider:AnalyticsProvider
    var defaultParameters:Map<String, Any>

    fun setDefaultParameters(event:AnalyticsEventInterface)
    fun logEvent(event:AnalyticsEventInterface)
    fun setUpIdentification(userIdentification: UserIdentification)
    fun trackScreen(screen: AnalyticsScreenInterface)
}