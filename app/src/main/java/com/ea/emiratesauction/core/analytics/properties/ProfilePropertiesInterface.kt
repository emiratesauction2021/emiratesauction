package com.ea.emiratesauction.core.analytics.properties

import com.ea.emiratesauction.core.analytics.providers.AnalyticsProviderIdentifier

/**
 * Profile properties interface
 *
 * @constructor Create empty Profile properties interface
 */
interface ProfilePropertiesInterface:BasePropertiesInterface {
    var providersIdentifiers: List<AnalyticsProviderIdentifier>
}