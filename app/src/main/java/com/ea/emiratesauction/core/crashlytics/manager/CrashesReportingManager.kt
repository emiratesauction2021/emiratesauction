package com.ea.emiratesauction.core.crashlytics.manager

import com.ea.emiratesauction.core.crashlytics.client.CrashModel
import com.ea.emiratesauction.core.crashlytics.provider.CrashProviders
import com.ea.emiratesauction.core.crashlytics.provider.CrashReportClientInterface


object CrashesReportingManager : CrashesReportingManagerInterface {

    lateinit var crashReportClientInterface: ArrayList<CrashReportClientInterface>

    /**
     * Apply all functions on provided Crash reporting providers
     */
    override fun setProviders(clients: ArrayList<CrashReportClientInterface>) {
        crashReportClientInterface = clients
    }

    /**
     * Get list of Crash reporting Interfaces from provided providers
     */
    private fun getClients(crashClients: ArrayList<CrashProviders>?): ArrayList<CrashReportClientInterface> {
        return if (crashClients == null) {
            crashReportClientInterface
        } else {
            val retList = ArrayList<CrashReportClientInterface>()
            for (cl in crashClients) {
                val client = crashReportClientInterface.find {
                    it.type == cl
                }
                client?.let { retList.add(it) }
            }
            retList
        }

    }


    /**
     * Start Crash reporting for provided providers with custom keys
     */
    override fun startCrashesReportingWithCustomKeys(crashModel: CrashModel) {
        for (cl in getClients(crashModel.crashProviderClients)) {
            cl.startCrashesReportingWithCustomKeys(crashModel)
        }
    }

    /**
     * Add Custom logs to provided providers
     */
    override fun addCrashesReportingCustomLogMessage(crashModel: CrashModel) {
        for (cl in getClients(crashModel.crashProviderClients)) {
            cl.startCrashesReportingCustomLogMessage(crashModel)
        }
    }

    /**
     * Specify Crash reporting by user id
     */
    override fun setCrashesReportingUserId(crashModel: CrashModel) {
        for (cl in getClients(crashModel.crashProviderClients)) {
            cl.startCrashesReportingUserId(crashModel)
        }
    }

}