package com.ea.emiratesauction.core.analytics.screen

import com.ea.emiratesauction.core.analytics.managers.interfaces.AnalyticsProperties
import com.ea.emiratesauction.core.constants.analytics.AnalyticsProviderIdentifier

/**
    Define screen event which contain screen name and attributes
    provided with analytics provider
 */
abstract class BaseAnalyticsScreen {
    abstract var name: String
    abstract var properties: AnalyticsProperties
    open var providerIdentifiers: ArrayList<AnalyticsProviderIdentifier> = AnalyticsProviderIdentifier.values().toCollection(ArrayList())
}
