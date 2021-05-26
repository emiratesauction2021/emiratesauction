package com.ea.emiratesauction.core.analytics.providers

import com.ea.emiratesauction.core.analytics.event.AnalyticsEventInterface
import com.ea.emiratesauction.core.analytics.profile.ProfileIdentificationInterface
import com.ea.emiratesauction.core.analytics.properties.BasePropertiesInterface
import com.ea.emiratesauction.core.analytics.properties.ProfilePropertiesInterface
import com.ea.emiratesauction.core.analytics.screen.AnalyticsScreenInterface

interface AnalyticsProviderInterface {
    var providerID:AnalyticsProviderIdentifier
    var defaultEventProperties:BasePropertiesInterface
    var defaultScreenProperties:BasePropertiesInterface

    fun logEvent(event:AnalyticsEventInterface)
    fun trackScreen(screen: AnalyticsScreenInterface)

    fun setupProfileIdentification(profileID:ProfileIdentificationInterface)
    fun setProfileProperties(profileIdentification: ProfilePropertiesInterface)

}