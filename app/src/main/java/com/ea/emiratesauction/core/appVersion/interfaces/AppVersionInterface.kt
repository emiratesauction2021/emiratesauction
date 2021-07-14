package com.ea.emiratesauction.core.appVersion.interfaces

import android.view.ViewTreeObserver
import com.ea.emiratesauction.core.appVersion.appVersionManager.AppVersionUpdate

/**
 * The interface which contains all the needed  methods to be a supported app version.
 */
interface AppVersionInterface {
    /**
     * Get changes in current app version from major, minor and patch
     */
    fun getAppVersionUpdate(): ArrayList<AppVersionUpdate>

    /**
     * Get last app version
     * Note : if app version in Google play store invalid  will return "0.0.0"
     * @return App version in Google play store
     */
    fun getLastAppVersion(): String
}
