package com.ea.emiratesauction.common.utils

import com.ea.emiratesauction.BuildConfig

class BusinessConstants {
    companion object {

        const val PUBNUB_CHANNEL_NAME = "PubNubdriver"
//        const val PubNUB_SUBSCRIBE_KEY="sub-c-e1444942-27ae-11ea-9e12-76e5f2bf83fc"
//        const val PubNUB_PUBLISH_KEY="pub-c-31bc11ae-5163-4cb5-b39e-aab3e1457437"
//        const val PubNUB_SECREATE_KEY="sub-c-e1444942-27ae-11ea-9e12-76e5f2bf83fc"

        const val PubNUB_SUBSCRIBE_KEY = BuildConfig.PubNUB_SUBSCRIBE_KEY
        const val PubNUB_PUBLISH_KEY = BuildConfig.PubNUB_PUBLISH_KEY
        const val PubNUB_SECRETE_KEY = BuildConfig.PubNUB_SECRETE_KEY


    }

}