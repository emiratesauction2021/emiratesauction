package com.ea.emiratesauction.core.analytics.managers

import com.ea.emiratesauction.core.analytics.event.BaseAnalyticsEvent
import com.ea.emiratesauction.core.analytics.managers.interfaces.AnalyticsManagerInterface
import com.ea.emiratesauction.core.analytics.profile.identification.BaseAnalyticsProfileIdentification
import com.ea.emiratesauction.core.analytics.profile.properties.BaseAnalyticsProfileProperties
import com.ea.emiratesauction.core.constants.analytics.providers.AnalyticsProviderIdentifier
import com.ea.emiratesauction.core.analytics.providers.AnalyticsProviderInterface
import com.ea.emiratesauction.core.analytics.screen.BaseAnalyticsScreen
import java.lang.Exception

/**
 * The Analytics Manager - it's the middle layer between the app and the analytics providers
 *
 * it's a Orderedton instance which is used across the whole app
 */
object AnalyticsManager : AnalyticsManagerInterface{
    // Define analytics providers
    private val providers = HashMap<AnalyticsProviderIdentifier, AnalyticsProviderInterface>()

    override fun setAnalyticsProviders(providers: Map<AnalyticsProviderIdentifier, AnalyticsProviderInterface>) {
        this.resetProviders()
        this.providers.putAll(providers)
    }

    override fun logEvent(event: BaseAnalyticsEvent) {
        for (providersName in event.providerIdentifiers) {
            providers[providersName]?.let {
                it.logEvent(event)
            } ?: throw Exception("Provider $providersName has not been initialized yet")
        }
    }

    override fun trackScreen(screen: BaseAnalyticsScreen) {
        for (providersName in screen.providerIdentifiers) {
            providers[providersName]?.let {
                it.trackScreen(screen)
            } ?: throw Exception("Provider $providersName has not been initialized yet")
        }
    }

    override fun setUpProfileIdentification(profileIdentification: BaseAnalyticsProfileIdentification) {
        for (providersName in profileIdentification.providerIdentifiers) {
            providers[providersName]?.let {
                it.setupProfileIdentification(profileIdentification)
            } ?: throw Exception("Provider $providersName has not been initialized yet")
        }
    }

    override fun setProfileProperties(profileProperties: BaseAnalyticsProfileProperties) {
        for (providersName in profileProperties.providerIdentifiers) {
            providers[providersName]?.let {
                it.setProfileProperties(profileProperties)
            } ?: throw Exception("Provider $providersName has not been initialized yet")
        }
    }

    override fun removeProvider(providerID: AnalyticsProviderIdentifier) {
        this.providers.remove(providerID)
    }

    override fun resetProviders() {
        this.providers.clear()
    }
}