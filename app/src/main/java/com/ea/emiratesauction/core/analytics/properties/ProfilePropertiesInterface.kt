package com.ea.emiratesauction.core.analytics.properties

import com.ea.emiratesauction.core.analytics.providers.AnalyticsProviderIdentifier

interface ProfilePropertiesInterface:BasePropertiesInterface {
    var providersIdentifiers: List<AnalyticsProviderIdentifier>
}