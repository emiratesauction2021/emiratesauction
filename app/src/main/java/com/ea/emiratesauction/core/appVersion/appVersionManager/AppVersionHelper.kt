package com.ea.emiratesauction.core.appVersion.appVersionManager

import com.ea.emiratesauction.core.appVersion.interfaces.AppVersionsValidateInterface

class AppVersionHelper :
    AppVersionsValidateInterface {

    /**
     *  check if version valid or invalid.
     * parameter version: app version.
     *  returns: AppVersionValidation
     */

    override fun validateAppVersion(version: String): AppVersionValidation {
        return when (isAppVersion(version)) {
            true -> AppVersionValidation.VALID
            false -> AppVersionValidation.INVALID
        }
    }

    /**
     *  check if version valid or invalid by regular expresion
     *  parameter version: app version.
     *  returns: Bool
     */

    private fun isAppVersion(version: String): Boolean {
        var appVersionRegEX = "[1-9].[0-9].[0-9]".toRegex()
        return appVersionRegEX.containsMatchIn(version)
    }

    /**
     *  Get  point changed in version from major, minor and patch
     *  parameter current: current version in app
     *  parameter last: last version in Google play store
     */

    fun getChangeAppVersionUpdate(current: String, last: String): ArrayList<AppVersionUpdate> {
        return arrayListOf(
            AppVersionUpdate.MAJOR,
            AppVersionUpdate.PATCH
        )
    }

}