package com.ea.emiratesauction.core.analytics.profile

import com.ea.emiratesauction.core.analytics.providers.AnalyticsProviderIdentifier

/**
 * Profile identification interface
 *
 * @constructor Create empty Profile identification interface
 */
interface ProfileIdentificationInterface {
    var profileID: String
    var providersIdentifiers: List<AnalyticsProviderIdentifier>
}