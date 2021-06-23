package com.ea.emiratesauction.core.crashlytics.provider

import com.ea.emiratesauction.core.crashlytics.client.CrashModel

/**
 * Define abstracts for client
 */
abstract class CrashReportClientInterface {

    abstract val type: CrashProviders

    protected abstract fun initCrashesReportingProvider()

    abstract fun startCrashesReportingWithCustomKeys(crashModel: CrashModel)

    abstract fun startCrashesReportingCustomLogMessage(crashModel: CrashModel)

    abstract fun startCrashesReportingUserId(crashModel: CrashModel)

}