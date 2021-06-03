package com.ea.emiratesauction.core.analytics.managers.interfaces

import com.ea.emiratesauction.core.analytics.event.BaseAnalyticsEvent
import com.ea.emiratesauction.core.analytics.profile.identification.BaseAnalyticsProfileIdentification
import com.ea.emiratesauction.core.analytics.profile.properties.BaseAnalyticsProfileProperties
import com.ea.emiratesauction.core.constants.analytics.AnalyticsProviderIdentifier
import com.ea.emiratesauction.core.analytics.providers.AnalyticsProviderInterface
import com.ea.emiratesauction.core.analytics.screen.BaseAnalyticsScreen
/**
    Define analytics manager interface methods
 */

// Analytics Properties type alias
typealias AnalyticsProperties = Map<String, Any>

interface AnalyticsManagerInterface {
    fun setAnalyticsProviders(providers: Map<AnalyticsProviderIdentifier, AnalyticsProviderInterface>)
    fun logEvent(event: BaseAnalyticsEvent)
    fun trackScreen(screen: BaseAnalyticsScreen)
    fun setUpProfileIdentification(profileIdentification: BaseAnalyticsProfileIdentification)
    fun setProfileProperties(profileProperties: BaseAnalyticsProfileProperties)
    fun removeProvider(providerID: AnalyticsProviderIdentifier)
    fun resetProviders()
}