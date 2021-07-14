package com.ea.emiratesauction.core.analytics.screen

import com.ea.emiratesauction.core.analytics.managers.interfaces.AnalyticsProperties
import com.ea.emiratesauction.core.constants.analytics.providers.AnalyticsProviderIdentifier

/**
 * The base analytics screen which is used by the analytics manager through out the whole app, it contains the different required information for an analytics screen
 * such as screen name, properties ...etc
 */
abstract class BaseAnalyticsScreen {
    /**
     * The screen name
     */
    abstract var name: String

    /**
     * The properties to be linked with a screen
     */
    abstract var properties: AnalyticsProperties

    /**
     * The provider identifiers which that screen is supporting, by default it contains all the identifiers
     *
     * Note: Only update it if you want specific providers to fire this screen
     *
     * @see AnalyticsProviderIdentifier
     */
    open var providerIdentifiers: ArrayList<AnalyticsProviderIdentifier> = AnalyticsProviderIdentifier.values().toCollection(ArrayList())
}