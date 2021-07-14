package com.ea.emiratesauction.common.utils

import com.ea.emiratesauction.BuildConfig

class BusinessConstants {
    companion object {

        const val PUBNUB_CHANNEL_NAME = "PubNubdriver"

        const val PubNUB_SUBSCRIBE_KEY = BuildConfig.PubNUB_SUBSCRIBE_KEY
        const val PubNUB_PUBLISH_KEY = BuildConfig.PubNUB_PUBLISH_KEY
        const val PubNUB_SECRETE_KEY = BuildConfig.PubNUB_SECRETE_KEY
        const val EA_SHARED_PREFERENCE = "EASharedPreferences"
        const val SECURE_PREFERENCES_NAME = "secure_preferences"

    }

}