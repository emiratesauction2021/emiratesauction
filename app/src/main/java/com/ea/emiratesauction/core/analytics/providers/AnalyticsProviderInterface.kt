package com.ea.emiratesauction.core.analytics.providers

import com.ea.emiratesauction.core.analytics.event.BaseAnalyticsEvent
import com.ea.emiratesauction.core.analytics.profile.identification.BaseAnalyticsProfileIdentification
import com.ea.emiratesauction.core.analytics.defaultProperties.event.BaseAnalyticsDefaultEventProperties
import com.ea.emiratesauction.core.analytics.defaultProperties.screen.BaseAnalyticsDefaultScreenProperties
import com.ea.emiratesauction.core.analytics.profile.properties.BaseAnalyticsProfileProperties
import com.ea.emiratesauction.core.analytics.screen.BaseAnalyticsScreen
import com.ea.emiratesauction.core.constants.analytics.AnalyticsProviderIdentifier

/**
 * The interface which contains all the needed parameters and methods to be a supported analytics provider in the analytics manager
 */
interface AnalyticsProviderInterface {
    /**
     * The provider identifier from the AnalyticsProviderIdentifier enum
     */
    var providerID: AnalyticsProviderIdentifier

    /**
     * The default parameters which will be sent with every event - it will be appended on the event parameters
     */
    var defaultEventProperties: BaseAnalyticsDefaultEventProperties

    /**
     * The default parameters which will be sent with every screen - it will be appended on the screen parameters
     */
    var defaultScreenProperties: BaseAnalyticsDefaultScreenProperties

    /**
     * Logs an event
     *
     * Note: It will throw an exception if one of the analytics providers used by the event isn't initialized yet by the manager
     *
     * @param event The event which has to be logged, it has to implement the BaseAnalyticsEvent
     */
    fun logEvent(event: BaseAnalyticsEvent)

    /**
     * Tracks a screen
     *
     * Note: It will throw an exception if one of the analytics providers used by the screen isn't initialized yet by the manager
     *
     * @param screen The screen which has to be tracked, it has to implement the BaseAnalyticsScreen
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
     */
    fun setupProfileIdentification(profileIdentification: BaseAnalyticsProfileIdentification)

    /**
     * Setup the profile default properties of the user such as age, profile info ...etc - It's handled per provider
     *
     * This method could be called more than once for each provider(s) separately
     *
     * Note: It will throw an exception if one of the analytics providers used by the properties isn't initialized yet by the manager
     *
     * @param profileProperties The profile properties object which contains the default properties for the user in different provider(s), it has to
     * implement the BaseAnalyticsProfileProperties
     */
    fun setProfileProperties(profileProperties: BaseAnalyticsProfileProperties)
}