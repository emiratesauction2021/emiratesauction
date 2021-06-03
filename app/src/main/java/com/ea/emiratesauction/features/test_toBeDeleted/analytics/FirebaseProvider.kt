package com.ea.emiratesauction.features.test_toBeDeleted.analytics

import com.ea.emiratesauction.core.analytics.defaultProperties.event.BaseAnalyticsDefaultEventProperties
import com.ea.emiratesauction.core.analytics.defaultProperties.screen.BaseAnalyticsDefaultScreenProperties
import com.ea.emiratesauction.core.analytics.event.BaseAnalyticsEvent
import com.ea.emiratesauction.core.analytics.managers.interfaces.AnalyticsProperties
import com.ea.emiratesauction.core.analytics.profile.identification.BaseAnalyticsProfileIdentification
import com.ea.emiratesauction.core.analytics.profile.properties.BaseAnalyticsProfileProperties
import com.ea.emiratesauction.core.analytics.providers.AnalyticsProviderInterface
import com.ea.emiratesauction.core.analytics.screen.BaseAnalyticsScreen
import com.ea.emiratesauction.core.constants.analytics.AnalyticsProviderIdentifier

class FirebaseProvider : AnalyticsProviderInterface {

    override var providerID: AnalyticsProviderIdentifier
        get() = TODO("Not yet implemented")
        set(value) {}

    override var defaultEventProperties: BaseAnalyticsDefaultEventProperties
        get() = TODO("Not yet implemented")
        set(value) {}

    override var defaultScreenProperties: BaseAnalyticsDefaultScreenProperties = DefaultScreenProperties()

    override fun logEvent(eventBase: BaseAnalyticsEvent) {
        TODO("Not yet implemented")
    }

    override fun trackScreen(screenBase: BaseAnalyticsScreen) {
        TODO("Not yet implemented")
    }

    override fun setupProfileIdentification(baseAnalyticsProfileID: BaseAnalyticsProfileIdentification) {
        TODO("Not yet implemented")
    }

    override fun setProfileProperties(baseAnalyticsProfileIdentification: BaseAnalyticsProfileProperties) {
        TODO("Not yet implemented")
    }



}

class DefaultScreenProperties: BaseAnalyticsDefaultScreenProperties() {
    override var properties: AnalyticsProperties = hashMapOf()
}