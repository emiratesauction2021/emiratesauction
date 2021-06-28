package com.ea.emiratesauction.core.analytics.managers.interfaces

import com.ea.emiratesauction.core.analytics.event.BaseAnalyticsEvent
import com.ea.emiratesauction.core.analytics.profile.identification.BaseAnalyticsProfileIdentification
import com.ea.emiratesauction.core.analytics.profile.properties.BaseAnalyticsProfileProperties
import com.ea.emiratesauction.core.constants.analytics.providers.AnalyticsProviderIdentifier
import com.ea.emiratesauction.core.analytics.providers.AnalyticsProviderInterface
import com.ea.emiratesauction.core.analytics.screen.BaseAnalyticsScreen
import java.lang.Exception

/**
 * Analytics properties type alias -> Map<String, Any>
 */
typealias AnalyticsProperties = Map<String, Any>

/**
 * Analytics Manager Interface which defines set of methods have to be provided by every analytics manager.
 */
interface AnalyticsManagerInterface {
    /**
     * Set the analytics providers implementations associated with there provider identifier - it will reset all the current providers before setting the new value
     *
     * This method has to be called before doing any logging
     *
     * @param providers A map contains the provider identifier from the AnalyticsProviderIdentifier enum and the actual implementation of the provider itself
     */
    fun setAnalyticsProviders(providers: Map<AnalyticsProviderIdentifier, AnalyticsProviderInterface>)

    /**
     * Logs an event
     *
     * Note: It will throw an exception if one of the analytics providers used by the event isn't initialized yet by the manager
     *
     * @param event The event which has to be logged, it has to implement the BaseAnalyticsEvent
     *
     * @throws Exception When one of the analytics providers used by the event isn't initialized yet by the manager
     */
    fun logEvent(event: BaseAnalyticsEvent)

    /**
     * Tracks a screen
     *
     * Note: It will throw an exception if one of the analytics providers used by the screen isn't initialized yet by the manager
     *
     * @param screen The screen which has to be tracked, it has to implement the BaseAnalyticsScreen
     *
     * @throws Exception When one of the analytics providers used by the event isn't initialized yet by the manager
     */
    fun trackScreen(screen: BaseAnalyticsScreen)

    /**
     * Setup the profile identification of the user such as user ID ... etc - It's handled per provider
     *
     * This method could be called more than once for each provider(s) separately
     *
     * Note: It will throw an exception if one of the analytics providers used by the identification isn't initialized yet by the manager
     *
     * @param profileIdentification The identification object which contains the IDs for the user in different provider(s), it has to
     * implement the BaseAnalyticsProfileIdentification
     *
     * @throws Exception When one of the analytics providers used by the event isn't initialized yet by the manager
     */
    fun setUpProfileIdentification(profileIdentification: BaseAnalyticsProfileIdentification)

    /**
     * Setup the profile default properties of the user such as age, profile info ...etc - It's handled per provider
     *
     * This method could be called more than once for each provider(s) separately
     *
     * Note: It will throw an exception if one of the analytics providers used by the properties isn't initialized yet by the manager
     *
     * @param profileProperties The profile properties object which contains the default properties for the user in different provider(s), it has to
     * implement the BaseAnalyticsProfileProperties
     *
     * @throws Exception When one of the analytics providers used by the event isn't initialized yet by the manager
     */
    fun setProfileProperties(profileProperties: BaseAnalyticsProfileProperties)

    /**
     * Removes a provider from the pre-initialized providers
     *
     * @param providerID The provider ID to be deleted - it has to be from the AnalyticsProviderIdentifier enum
     *
     * @throws Exception When one of the analytics providers used by the event isn't initialized yet by the manager
     */
    fun removeProvider(providerID: AnalyticsProviderIdentifier)

    /**
     * Reset all the providers
     *
     * Warning: You have to call the setAnalyticsProviders again before using any logging methods after resetting otherwise the app will throw exceptions as the providers
     * isn't yet initialized
     */
    fun resetProviders()
}