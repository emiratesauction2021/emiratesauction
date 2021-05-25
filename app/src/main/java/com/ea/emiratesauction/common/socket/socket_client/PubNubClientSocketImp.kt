package com.ea.emiratesauction.common.socket.socket_client

import android.util.Log
import com.ea.emiratesauction.common.socket.socket_manager.SocketListenerInterface
import com.ea.emiratesauction.common.utils.BusinessConstants
import com.pubnub.api.PubNub
import com.pubnub.api.callbacks.SubscribeCallback
import com.pubnub.api.models.consumer.PNStatus
import com.pubnub.api.models.consumer.pubsub.PNMessageResult
import com.pubnub.api.models.consumer.pubsub.PNPresenceEventResult
import com.pubnub.api.models.consumer.pubsub.PNSignalResult
import com.pubnub.api.models.consumer.pubsub.message_actions.PNMessageActionResult
import com.pubnub.api.models.consumer.pubsub.objects.PNMembershipResult
import com.pubnub.api.models.consumer.pubsub.objects.PNSpaceResult
import com.pubnub.api.models.consumer.pubsub.objects.PNUserResult


import java.util.*

class PubNubClientSocketImp : SocketClientInterface {
     var pubNubClient: PubNubSocketClient

    init {
        PubNubSocketClient.pnConfiguration.subscribeKey = BusinessConstants.PubNUB_SUBSCRIBE_KEY
        PubNubSocketClient.pnConfiguration.publishKey = BusinessConstants.PubNUB_PUBLISH_KEY
        PubNubSocketClient.pnConfiguration.secretKey = BusinessConstants.PubNUB_SECREATE_KEY
        pubNubClient = PubNubSocketClient(PubNubSocketClient.pnConfiguration)

    }

    override fun subscribeToChannel(channel: String) {
        pubNubClient.run {
            subscribe().channels(Arrays.asList(channel)).execute()
            configuration.connectTimeout = 10 * 60
        }

    }

    override fun unSubscribeToChannel(channel: String) {
        pubNubClient.run {
            unsubscribe().channels(Arrays.asList(channel)).execute()
        }
    }

    override fun getResult(mSocketListener: SocketListenerInterface) {

        pubNubClient.run {
            val mPubNubCallback = object : SubscribeCallback() {
                override fun signal(pubnub: PubNub, pnSignalResult: PNSignalResult) {}

                override fun user(pubnub: PubNub, pnUserResult: PNUserResult) {}

                override fun membership(pubnub: PubNub, pnMembershipResult: PNMembershipResult) {}

                override fun space(pubnub: PubNub, pnSpaceResult: PNSpaceResult) {}


                override fun status(pubnub: PubNub, pnStatus: PNStatus) {

                }

                override fun message(pubnub: PubNub, message: PNMessageResult) {
                    mSocketListener.getSocketData(message.message.toString())
                    Log.e("message", message.message.toString());
                }

                override fun messageAction(
                    pubnub: PubNub,
                    pnMessageActionResult: PNMessageActionResult
                ) {
                }

                override fun presence(
                    pubnub: PubNub,
                    pnPresenceEventResult: PNPresenceEventResult
                ) {
                }
            }
            this.addListener(mPubNubCallback!!)
        }
    }

    override fun startListening() {

    }

    override fun stopListening() {

    }
}
