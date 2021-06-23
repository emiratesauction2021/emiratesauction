package com.ea.emiratesauction.core.crashlytics.client

import android.util.Log
import com.ea.emiratesauction.core.crashlytics.provider.CrashProviders
import com.ea.emiratesauction.core.crashlytics.provider.CrashReportClientInterface

/**
 * Crash Reporting Client
 */
class MixPanelClient : CrashReportClientInterface() {
    /**
     * Define Client as a Type of CrashProvider
     */
    override val type: CrashProviders
        get() = CrashProviders.MIXPANEL


    /**
     * Have to call this function while init Class
     */
    init {
        initCrashesReportingProvider()
    }


    override fun initCrashesReportingProvider() {

    }

    /**
     * Start Reporting
     */
    override fun startCrashesReportingWithCustomKeys(crashModel: CrashModel) {
        Log.e("startCrashes", "MixPanel" + crashModel.crashReportingCustomKeyValue?.second.toString())
    }

    override fun startCrashesReportingCustomLogMessage(crashModel: CrashModel) {

    }

    override fun startCrashesReportingUserId(crashModel: CrashModel) {

    }


}