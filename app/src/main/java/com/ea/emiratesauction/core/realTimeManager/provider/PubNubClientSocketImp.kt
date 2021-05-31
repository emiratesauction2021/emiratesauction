package com.ea.emiratesauction.core.realTimeManager.provider

import android.util.Log
import com.ea.emiratesauction.common.utils.BusinessConstants
import com.ea.emiratesauction.core.realTimeManager.data.RTMChannel
import com.ea.emiratesauction.core.realTimeManager.data.RTMEvent
import com.ea.emiratesauction.core.realTimeManager.interfaces.RTMProviderCallBack
import com.ea.emiratesauction.core.realTimeManager.interfaces.RTMProviderInterface
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
import java.lang.Exception
import java.util.*


class PubNubClientSocketImp : RTMProviderInterface {
    override var responseListener: RTMProviderCallBack? = null

    lateinit var pubNubClient: PubNubSocketClient

    init {
        initialize()
    }

    override fun listen(event: RTMEvent) {
        pubNubClient.run {
            val mPubNubCallback = object : SubscribeCallback() {
                override fun signal(pubnub: PubNub, pnSignalResult: PNSignalResult) {}

                override fun user(pubnub: PubNub, pnUserResult: PNUserResult) {}

                override fun membership(pubnub: PubNub, pnMembershipResult: PNMembershipResult) {}

                override fun space(pubnub: PubNub, pnSpaceResult: PNSpaceResult) {}


                override fun status(pubnub: PubNub, pnStatus: PNStatus) {

                }

                override fun message(pubnub: PubNub, message: PNMessageResult) {

                    if (responseListener==null){
                        throw Exception("Provider call back not implemented")
                    }
                    else{
                        responseListener!!.onDataReceived(message.channel,"",message.message)
                    }

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

    override fun initialize() {
        PubNubSocketClient.pnConfiguration.subscribeKey = BusinessConstants.PubNUB_SUBSCRIBE_KEY
        PubNubSocketClient.pnConfiguration.publishKey = BusinessConstants.PubNUB_PUBLISH_KEY
        PubNubSocketClient.pnConfiguration.secretKey = BusinessConstants.PubNUB_SECREATE_KEY
        pubNubClient = PubNubSocketClient(PubNubSocketClient.pnConfiguration)

    }

    override fun connect() {
        TODO("Not yet implemented")
    }

    override fun disconnect() {
        TODO("Not yet implemented")
    }

    override fun subscribe(channel: RTMChannel) {
        pubNubClient.run {
            subscribe().channels(Arrays.asList(channel.name.name)).execute()
            configuration.connectTimeout = 10 * 60
        }

    }

    override fun unSubscribe(channel: RTMChannel) {
        pubNubClient.run {
            unsubscribe().channels(Arrays.asList(channel.name.name)).execute()
        }
    }

    override fun stopListen(event: RTMEvent) {
        TODO("Not yet implemented")
    }

}
