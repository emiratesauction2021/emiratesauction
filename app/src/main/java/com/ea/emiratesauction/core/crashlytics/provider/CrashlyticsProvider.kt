package com.ea.emiratesauction.core.crashlytics.provider

interface CrashlyticsProvider {
    fun initCrashlytics()
    fun startCrashlyticsWithCustomKeys(customKey: String, customValue: String)
    fun addCrashlyticsCustomLogMessage(customMessage: String)
    fun setCrashlyticsUserId(userId: String)
    fun reportNonFatalExceptions(methodThatThrows: () -> Unit)
    fun enableCrashlyticsOPTReporting(isEnabled: Boolean)
}