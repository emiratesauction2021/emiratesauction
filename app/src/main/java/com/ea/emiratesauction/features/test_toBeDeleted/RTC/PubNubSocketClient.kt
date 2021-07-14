package com.ea.emiratesauction.features.test_toBeDeleted.RTC

import com.ea.emiratesauction.common.utils.BusinessConstants
import com.pubnub.api.PNConfiguration
import com.pubnub.api.PubNub


class PubNubSocketClient(initialConfig: PNConfiguration) : PubNub(initialConfig) {
    companion object {
        @Volatile
        var pnConfiguration = PNConfiguration()
        var instance: PubNub? = null
        val LOCK = Any()
        fun invoke() = instance ?: synchronized(LOCK) {
            pnConfiguration.subscribeKey = BusinessConstants.PubNUB_SUBSCRIBE_KEY
            pnConfiguration.publishKey = BusinessConstants.PubNUB_PUBLISH_KEY
            pnConfiguration.secretKey = BusinessConstants.PubNUB_SECRETE_KEY
            instance ?:PubNub(pnConfiguration).also {
                    instance = it
                }
        }
        fun configPubNub(): PubNub {
            pnConfiguration.subscribeKey = BusinessConstants.PubNUB_SUBSCRIBE_KEY
            pnConfiguration.publishKey = BusinessConstants.PubNUB_PUBLISH_KEY
            pnConfiguration.secretKey = BusinessConstants.PubNUB_SECRETE_KEY
            return  PubNub(pnConfiguration)
        }


    }
}