package com.ea.emiratesauction.core.crashlytics.manager


class CrashesReportingManagerImp(private val crashesReportingManager: CrashesReportingManager) {

    init {
        fun initCrashesReporting() {
            crashesReportingManager.initCrashesReporting()
        }
    }

    fun startCrashesReportingWithCustomKeys(customKey: String, customValue: String) {
        crashesReportingManager.startCrashesReportingWithCustomKeys(customKey, customValue)
    }

    fun addCrashesReportingCustomLogMessage(customMessage: String) {
        crashesReportingManager.addCrashesReportingCustomLogMessage(customMessage)
    }

    fun setCrashesReportingUserId(userId: String) {
        crashesReportingManager.setCrashesReportingUserId(userId)
    }

    fun reportNonFatalExceptions(methodThatThrows: () -> Unit) {
        crashesReportingManager.reportNonFatalExceptions(methodThatThrows)
    }

    fun enableCrashesReportingOPTReporting(isEnabled: Boolean) {
        crashesReportingManager.enableCrashesReportingOPTReporting(isEnabled)
    }


}