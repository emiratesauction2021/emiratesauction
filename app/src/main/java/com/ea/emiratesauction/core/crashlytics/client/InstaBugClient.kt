package com.ea.emiratesauction.core.crashlytics.client

import android.util.Log
import com.ea.emiratesauction.core.crashlytics.provider.CrashProviders
import com.ea.emiratesauction.core.crashlytics.provider.CrashReportClientInterface

/**
 * Crash Reporting Client
 */
class InstaBugClient : CrashReportClientInterface() {
    /**
     * Have to call this function while init Class
     */
    init {
        initCrashesReportingProvider()
    }

    /**
     * Define Client as a Type of CrashProvider
     */
    override val type: CrashProviders
        get() = CrashProviders.INSTABUG

    override fun initCrashesReportingProvider() {

    }

    /**
     * Start Reporting
     */
    override fun startCrashesReportingWithCustomKeys(crashModel: CrashModel) {
        Log.e(
            "startCrashes",
            "instaBug" + crashModel.crashReportingCustomKeyValue?.second.toString()
        )
    }

    override fun startCrashesReportingCustomLogMessage(crashModel: CrashModel) {

    }

    override fun startCrashesReportingUserId(crashModel: CrashModel) {

    }


}