package com.ea.emiratesauction.common.socket.socket_client

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
            pnConfiguration.secretKey = BusinessConstants.PubNUB_SECREATE_KEY
            instance ?:PubNub(pnConfiguration).also {
                    instance = it
                }
        }
        fun configPubNub(): PubNub {
            pnConfiguration.subscribeKey = BusinessConstants.PubNUB_SUBSCRIBE_KEY
            pnConfiguration.publishKey = BusinessConstants.PubNUB_PUBLISH_KEY
            pnConfiguration.secretKey = BusinessConstants.PubNUB_SECREATE_KEY
            return  PubNub(pnConfiguration)
        }


    }
}