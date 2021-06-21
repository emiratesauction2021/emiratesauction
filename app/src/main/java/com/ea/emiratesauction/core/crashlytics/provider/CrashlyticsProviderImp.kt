package com.ea.emiratesauction.core.crashlytics.provider

import com.ea.emiratesauction.core.crashlytics.manager.CrashesReportingManager


class CrashlyticsProviderImp(private val crashlyticsProvider: CrashlyticsProvider) : CrashesReportingManager {

    override fun initCrashesReporting() {
        crashlyticsProvider.initCrashlytics()
    }

    override fun startCrashesReportingWithCustomKeys(customKey: String, customValue: String) {
        crashlyticsProvider.startCrashlyticsWithCustomKeys(customKey, customValue)
    }

    override fun addCrashesReportingCustomLogMessage(customMessage: String) {
        crashlyticsProvider.addCrashlyticsCustomLogMessage(customMessage)
    }

    override fun setCrashesReportingUserId(userId: String) {
        crashlyticsProvider.setCrashlyticsUserId(userId)
    }

    override fun reportNonFatalExceptions(methodThatThrows: () -> Unit) {
        crashlyticsProvider.reportNonFatalExceptions(methodThatThrows)
    }

    override fun enableCrashesReportingOPTReporting(isEnabled: Boolean) {
        crashlyticsProvider.enableCrashlyticsOPTReporting(isEnabled)
    }


}