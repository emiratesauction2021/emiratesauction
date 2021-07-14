package com.ea.emiratesauction.core.crashlytics.client

import com.ea.emiratesauction.core.crashlytics.provider.CrashProviders

/**
 * Model that define attr send to init Crash reporting Specific client
 */
class CrashModel {
    /**
     * Define client type by @File CrashProviders
     */
    var crashProviderClients: ArrayList<CrashProviders>? = null

    /**
     * Use Custom Key and value while reporting crash
     */
    var crashReportingCustomKeyValue: Pair<String, String>? = null

    /**
     * Use Custom log while reporting crash
     */
    var crashReportingCustomLogMessage: String? = null

    /**
     * Specify User id for crashes
     */
    var crashReportingUserId: String? = null
}
