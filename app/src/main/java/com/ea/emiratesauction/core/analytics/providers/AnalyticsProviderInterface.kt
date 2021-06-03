package com.ea.emiratesauction.core.analytics.providers

import com.ea.emiratesauction.core.analytics.event.BaseAnalyticsEvent
import com.ea.emiratesauction.core.analytics.profile.identification.BaseAnalyticsProfileIdentification
import com.ea.emiratesauction.core.analytics.defaultProperties.event.BaseAnalyticsDefaultEventProperties
import com.ea.emiratesauction.core.analytics.defaultProperties.screen.BaseAnalyticsDefaultScreenProperties
import com.ea.emiratesauction.core.analytics.profile.properties.BaseAnalyticsProfileProperties
import com.ea.emiratesauction.core.analytics.screen.BaseAnalyticsScreen
import com.ea.emiratesauction.core.constants.analytics.AnalyticsProviderIdentifier

/**
Define analytics provider client interface attributes and methods
 */

interface AnalyticsProviderInterface {
    var providerID: AnalyticsProviderIdentifier
    var defaultEventProperties: BaseAnalyticsDefaultEventProperties
    var defaultScreenProperties: BaseAnalyticsDefaultScreenProperties

    fun logEvent(eventBase:BaseAnalyticsEvent)
    fun trackScreen(screenBase: BaseAnalyticsScreen)

    fun setupProfileIdentification(baseAnalyticsProfileID: BaseAnalyticsProfileIdentification)
    fun setProfileProperties(baseAnalyticsProfileIdentification: BaseAnalyticsProfileProperties)
}