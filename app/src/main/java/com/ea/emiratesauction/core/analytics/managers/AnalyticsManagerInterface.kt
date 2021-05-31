package com.ea.emiratesauction.core.analytics.managers

import com.ea.emiratesauction.core.analytics.event.AnalyticsEventInterface
import com.ea.emiratesauction.core.analytics.profile.ProfileIdentificationInterface
import com.ea.emiratesauction.core.analytics.properties.ProfilePropertiesInterface
import com.ea.emiratesauction.core.analytics.providers.AnalyticsProviderIdentifier
import com.ea.emiratesauction.core.analytics.providers.AnalyticsProviderInterface
import com.ea.emiratesauction.core.analytics.screen.AnalyticsScreenInterface
/*
define analytics manager interface methods
 */
interface AnalyticsManagerInterface {

    fun setAnalyticsProvider( providers: Map<AnalyticsProviderIdentifier, AnalyticsProviderInterface>)
    fun logEvent(event: AnalyticsEventInterface)
    fun trackScreen(screen: AnalyticsScreenInterface)
    fun setUpProfileIdentification(profileIdentification: ProfileIdentificationInterface)
    fun setProfileProperties(properties: ProfilePropertiesInterface)

}