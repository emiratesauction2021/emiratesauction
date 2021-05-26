package com.ea.emiratesauction.core.analytics.managers

import com.ea.emiratesauction.core.analytics.event.AnalyticsEventInterface
import com.ea.emiratesauction.core.analytics.profile.ProfileIdentificationInterface
import com.ea.emiratesauction.core.analytics.properties.ProfilePropertiesInterface
import com.ea.emiratesauction.core.analytics.providers.AnalyticsProviderIdentifier
import com.ea.emiratesauction.core.analytics.providers.AnalyticsProviderInterface
import com.ea.emiratesauction.core.analytics.screen.AnalyticsScreenInterface

object AnalyticsManager :
    AnalyticsManagerInterface {

    private val providers = HashMap<AnalyticsProviderIdentifier, AnalyticsProviderInterface>()
    override fun setAnalyticsProvider(providers: Map<AnalyticsProviderIdentifier, AnalyticsProviderInterface>) {
        TODO("Not yet implemented")
    }


    override fun logEvent(event: AnalyticsEventInterface) {
        for (providersName in event.providerIdentifiers) {
            providers.get(providersName)!!.logEvent(event)
        }
    }


    override fun trackScreen(screen: AnalyticsScreenInterface) {
        TODO("Not yet implemented")
    }

    override fun setUpProfileIdentification(profileIdentification: ProfileIdentificationInterface) {
        TODO("Not yet implemented")
    }

    override fun setProfileProperties(properties: ProfilePropertiesInterface) {
        for (providersName in properties.providersIdentifiers) {
            providers.get(providersName)!!.setProfileProperties(properties)
        }
    }


}