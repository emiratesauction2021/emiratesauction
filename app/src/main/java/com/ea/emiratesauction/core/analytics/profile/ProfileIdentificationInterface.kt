package com.ea.emiratesauction.core.analytics.profile

import com.ea.emiratesauction.core.analytics.providers.AnalyticsProviderIdentifier

interface ProfileIdentificationInterface {
    var profileID: String
    var providersIdentifiers: List<AnalyticsProviderIdentifier>
}