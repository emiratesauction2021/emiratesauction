package com.ea.emiratesauction.core.analytics.managers

import com.ea.emiratesauction.core.analytics.event.AnalyticsEventInterface
import com.ea.emiratesauction.core.analytics.profile.ProfileIdentificationInterface
import com.ea.emiratesauction.core.analytics.properties.ProfilePropertiesInterface
import com.ea.emiratesauction.core.analytics.providers.AnalyticsProviderIdentifier
import com.ea.emiratesauction.core.analytics.providers.AnalyticsProviderInterface
import com.ea.emiratesauction.core.analytics.screen.AnalyticsScreenInterface

/**
 * Analytics manager
 *
 * @constructor Create empty Analytics manager
 */
object AnalyticsManager :
    AnalyticsManagerInterface {
    //define analytics provider
    private val providers = HashMap<AnalyticsProviderIdentifier, AnalyticsProviderInterface>()

    /**
     * Set analytics provider
     *
     * @param providers
     */
    override fun setAnalyticsProvider(providers: Map<AnalyticsProviderIdentifier, AnalyticsProviderInterface>) {
        TODO("Not yet implemented")
    }

    /**
     * Log event
     *
     * @param event
     */
    override fun logEvent(event: AnalyticsEventInterface) {
        for (providersName in event.providerIdentifiers) {
            providers.get(providersName)!!.logEvent(event)
        }
    }

    /**
     * Track screen
     *
     * @param screen
     */
    override fun trackScreen(screen: AnalyticsScreenInterface) {
        TODO("Not yet implemented")
    }

    /**
     * Set up profile identification
     *
     * @param profileIdentification
     */
    override fun setUpProfileIdentification(profileIdentification: ProfileIdentificationInterface) {
        TODO("Not yet implemented")
    }

    /**
     * Set profile properties
     *
     * @param properties
     */
    override fun setProfileProperties(properties: ProfilePropertiesInterface) {
        for (providersName in properties.providersIdentifiers) {
            providers.get(providersName)!!.setProfileProperties(properties)
        }
    }


}