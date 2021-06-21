package com.ea.emiratesauction.core.crashlytics.manager

interface CrashesReportingManager {
    fun initCrashesReporting()
    fun startCrashesReportingWithCustomKeys(customKey: String, customValue: String)
    fun addCrashesReportingCustomLogMessage(customMessage: String)
    fun setCrashesReportingUserId(userId: String)
    fun reportNonFatalExceptions(methodThatThrows: () -> Unit)
    fun enableCrashesReportingOPTReporting(isEnabled: Boolean)

    companion object {
        operator fun invoke() {
        }
    }
}