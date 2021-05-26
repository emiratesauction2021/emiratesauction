package com.ea.emiratesauction.core.analytics.managers

import com.ea.emiratesauction.core.analytics.UserIdentification
import com.ea.emiratesauction.core.analytics.event.AnalyticsEventInterface
import com.ea.emiratesauction.core.analytics.screen.AnalyticsScreenInterface

interface AnalyticsManagerInterface {
    fun setDefaultParameters(event: AnalyticsEventInterface)
    fun logEvent(event: AnalyticsEventInterface)
    fun setUpIdentification(userIdentification: UserIdentification)
    fun trackScreen(screen: AnalyticsScreenInterface)
}