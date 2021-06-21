package com.ea.emiratesauction.core.crashlytics.client

import com.ea.emiratesauction.core.crashlytics.provider.CrashlyticsProvider
import com.google.firebase.crashlytics.FirebaseCrashlytics


/**
 * Class that initialize FireBase Crashlytics , will provided in dependencies
 */
class FireBaseCrashlyticsClient  : CrashlyticsProvider {

    /**
     * init FireBase Crashlytics
     */
    override fun initCrashlytics() {
        FirebaseCrashlytics.getInstance()
    }

    /**
     * Custom keys help you get the specific state of your app leading up to a crash.
     * You can associate arbitrary key/value pairs with your crash reports,
     * Then use the custom keys to search and filter crash reports in the Firebase console.
     * You can search for issues that match a custom key.
     */
    override fun startCrashlyticsWithCustomKeys(customKey: String, customValue: String) {
        FirebaseCrashlytics.getInstance().setCustomKey(customKey, customValue);
    }

    /**
     * To give yourself more context for the events leading up to a crash, you can add custom Crashlytics logs to your app.
     * Crashlytics associates the logs with your crash data and displays them in the Crashlytics page
     */
    override fun addCrashlyticsCustomLogMessage(customMessage: String) {
        FirebaseCrashlytics.getInstance().log(customMessage)
    }

    /**
     * To diagnose an issue, it’s often helpful to know which of your users experienced a given crash.
     * Crashlytics includes a way to anonymously identify users in your crash reports.
     * To add user IDs to your reports, assign each user a unique identifier in the form of an ID number, token, or hashed value
     */
    override fun setCrashlyticsUserId(userId: String) {
        FirebaseCrashlytics.getInstance().setUserId(userId);
    }

    /**
     * In addition to automatically reporting your app’s crashes,
     * Crashlytics lets you record non-fatal exceptions and sends them to you the next time your app launches.
     */
    override fun reportNonFatalExceptions(methodThatThrows: () -> Unit) {
        try {
            methodThatThrows()
        } catch (e: Exception) {
            FirebaseCrashlytics.getInstance().recordException(e)
            // ...handle the exception.
        }
    }

    /**
     * By default, Crashlytics automatically collects crash reports for all your app's users.
     * To give users more control over the data they send,
     * You can enable opt-in reporting by disabling automatic reporting and only sending data to Crashlytics
     * When you choose to in your code
     * Enable collection for select users by calling the Crashlytics data collection override at runtime.
     * The override value persists across launches of your app so Crashlytics can automatically collect reports.
     * To opt out of automatic crash reporting, pass false as the override value. When set to false,
     * The new value does not apply until the next run of the app.
     */
    override fun enableCrashlyticsOPTReporting(isEnabled: Boolean) {
        FirebaseCrashlytics.getInstance().setCrashlyticsCollectionEnabled(isEnabled)
    }

}