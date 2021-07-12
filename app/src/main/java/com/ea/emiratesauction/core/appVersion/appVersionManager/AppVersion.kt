package com.ea.emiratesauction.core.appVersion.appVersionManager

import com.ea.emiratesauction.core.appVersion.interfaces.AppVersionInterface

/**
 *  The AppVersion - It is instance which is used across the whole app to get deference between Google play store and app changing in versions.
 */
class AppVersion : AppVersionInterface {

    var appValidateHelper: AppVersionHelper
    var currentAppVersion: String

    init {
        currentAppVersion = "1.0.0"
        appValidateHelper = AppVersionHelper()
    }


    override fun getAppVersionUpdate(): ArrayList<AppVersionUpdate> {
        val lastVersion = getLastAppVersion()
        val currentValidation: AppVersionValidation =
            (appValidateHelper.validateAppVersion("1.0.0"))
        if ((lastVersion == currentAppVersion) && currentValidation == AppVersionValidation.INVALID) {
            return arrayListOf(AppVersionUpdate.none)
        }
        return appValidateHelper.getChangeAppVersionUpdate(currentAppVersion, lastVersion)
    }

    override fun getLastAppVersion(): String {
        return when (appValidateHelper.validateAppVersion("1.0.0")) {
            AppVersionValidation.VALID ->
                "1.0.0"
            AppVersionValidation.INVALID ->
                ""
        }
    }


}