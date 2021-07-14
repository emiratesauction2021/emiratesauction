package com.ea.emiratesauction.core.crashlytics.manager

import com.ea.emiratesauction.core.crashlytics.client.CrashModel
import com.ea.emiratesauction.core.crashlytics.provider.CrashReportClientInterface

interface CrashesReportingManagerInterface {

    /**
     * set Providers for initiation
     */
    fun setProviders(clients: ArrayList<CrashReportClientInterface>)
    /**
     * start crash Reporting with Custom Keys and values
     */
    fun startCrashesReportingWithCustomKeys(crashModel: CrashModel)
    /**
     * start crash Reporting with Custom log messages
     */
    fun addCrashesReportingCustomLogMessage(crashModel: CrashModel)
    /**
     * start crash Reporting for specific user id
     */
    fun setCrashesReportingUserId(crashModel: CrashModel)

}