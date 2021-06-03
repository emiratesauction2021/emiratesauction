package com.ea.emiratesauction.core.analytics.managers

import com.ea.emiratesauction.core.analytics.event.BaseAnalyticsEvent
import com.ea.emiratesauction.core.analytics.managers.interfaces.AnalyticsManagerInterface
import com.ea.emiratesauction.core.analytics.profile.identification.BaseAnalyticsProfileIdentification
import com.ea.emiratesauction.core.analytics.profile.properties.BaseAnalyticsProfileProperties
import com.ea.emiratesauction.core.constants.analytics.AnalyticsProviderIdentifier
import com.ea.emiratesauction.core.analytics.providers.AnalyticsProviderInterface
import com.ea.emiratesauction.core.analytics.screen.BaseAnalyticsScreen
import java.lang.Exception

/**
 * Analytics manager
 *
 * @constructor Create empty Analytics manager
 */
object AnalyticsManager : AnalyticsManagerInterface{
    // Define analytics provider
    private val providers = HashMap<AnalyticsProviderIdentifier, AnalyticsProviderInterface>()

    /**
     * Set analytics provider
     *
     * @param providers
     */
    override fun setAnalyticsProviders(providers: Map<AnalyticsProviderIdentifier, AnalyticsProviderInterface>) {
        this.resetProviders()
        this.providers.putAll(providers)
    }

    /**
     * Log event
     *
     * @param event
     */
    override fun logEvent(event: BaseAnalyticsEvent) {
        for (providersName in event.providerIdentifiers) {
            providers[providersName]?.let {
                it.logEvent(event)
            } ?: throw Exception("Provider $providersName has not been initialized yet")
        }
    }

    /**
     * Track screen
     *
     * @param screen
     */
    override fun trackScreen(screen: BaseAnalyticsScreen) {
        for (providersName in screen.providerIdentifiers) {
            providers[providersName]?.let {
                it.trackScreen(screen)
            } ?: throw Exception("Provider $providersName has not been initialized yet")
        }
    }

    /**
     * Set up profile identification
     *
     * @param profileIdentification
     */
    override fun setUpProfileIdentification(profileIdentification: BaseAnalyticsProfileIdentification) {
        for (providersName in profileIdentification.providerIdentifiers) {
            providers[providersName]?.let {
                it.setupProfileIdentification(profileIdentification)
            } ?: throw Exception("Provider $providersName has not been initialized yet")
        }
    }

    /**
     * Set profile properties
     *
     * @param profileProperties
     */
    override fun setProfileProperties(profileProperties: BaseAnalyticsProfileProperties) {
        for (providersName in profileProperties.providerIdentifiers) {
            providers[providersName]?.let {
                it.setProfileProperties(profileProperties)
            } ?: throw Exception("Provider $providersName has not been initialized yet")
        }
    }

    /**
     * Set profile properties
     *
     * @param providerID
     */
    override fun removeProvider(providerID: AnalyticsProviderIdentifier) {
        this.providers.remove(providerID)
    }

    /**
     * Set profile properties
     *
     */
    override fun resetProviders() {
        this.providers.clear()
    }
}