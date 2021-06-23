package com.ea.emiratesauction.core.crashlytics.manager

import com.ea.emiratesauction.core.crashlytics.client.CrashModel
import com.ea.emiratesauction.core.crashlytics.provider.CrashProviders
import com.ea.emiratesauction.core.crashlytics.provider.CrashReportClientInterface


object CrashesReportingManager : CrashesReportingManagerInterface {

    lateinit var crashReportClientInterface: ArrayList<CrashReportClientInterface>

    override fun setProviders(clients: ArrayList<CrashReportClientInterface>) {
        crashReportClientInterface = clients
    }

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


    override fun startCrashesReportingWithCustomKeys(crashModel: CrashModel) {
        for (cl in getClients(crashModel.crashProviderClients)) {
            cl.startCrashesReportingWithCustomKeys(crashModel)
        }
    }

    override fun addCrashesReportingCustomLogMessage(crashModel: CrashModel) {
        for (cl in getClients(crashModel.crashProviderClients)) {
            cl.startCrashesReportingCustomLogMessage(crashModel)
        }
    }

    override fun setCrashesReportingUserId(crashModel: CrashModel) {
        for (cl in getClients(crashModel.crashProviderClients)) {
            cl.startCrashesReportingUserId(crashModel)
        }
    }

}