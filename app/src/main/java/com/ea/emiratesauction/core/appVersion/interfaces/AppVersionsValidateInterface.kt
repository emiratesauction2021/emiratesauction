package com.ea.emiratesauction.core.appVersion.interfaces

import com.ea.emiratesauction.core.appVersion.appVersionManager.AppVersionValidation

/**
 * The interface which contains all the needed methods to be a supported app version helper.
 */
interface AppVersionsValidateInterface {
    /**
     * Validate app version
     *
     * @param version
     * @return AppVersionValidation
     */
    fun validateAppVersion(version: String): AppVersionValidation
}