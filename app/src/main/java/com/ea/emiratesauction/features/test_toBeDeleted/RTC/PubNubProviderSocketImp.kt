package com.ea.emiratesauction.features.test_toBeDeleted.RTC

import android.util.Log
import com.ea.emiratesauction.common.utils.BusinessConstants
import com.ea.emiratesauction.core.realTimeManager.channel.RTCChannelInterface
import com.ea.emiratesauction.core.realTimeManager.configurations.RTCConnectionConfigurationsInterface
import com.ea.emiratesauction.core.realTimeManager.event.RTCEventInterface
import com.ea.emiratesauction.core.realTimeManager.providers.interfaces.RTCProviderCallBack
import com.ea.emiratesauction.core.realTimeManager.providers.interfaces.RTCProviderInterface
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


class PubNubProviderSocketImp(listener: RTCProviderCallBack, connectionConfiguration: RTCConnectionConfigurationsInterface) : RTCProviderInterface(listener, connectionConfiguration) {


    lateinit var pubNubClient: PubNubSocketClient

    init {
        initialize()
    }

    private fun initialize() {
        PubNubSocketClient.pnConfiguration.subscribeKey = BusinessConstants.PubNUB_SUBSCRIBE_KEY
        PubNubSocketClient.pnConfiguration.publishKey = BusinessConstants.PubNUB_PUBLISH_KEY
        PubNubSocketClient.pnConfiguration.secretKey = BusinessConstants.PubNUB_SECRETE_KEY

    }



    override fun connect() {
        pubNubClient = PubNubSocketClient(PubNubSocketClient.pnConfiguration)
    }

    override fun subscribeToChannel(channel: RTCChannelInterface) {
        print("Not supported in pubnub")
    }

    override fun unsubscribeFromChannel(channel: RTCChannelInterface) {
        print("Not supported in pubnub")
    }

    override fun emit(event: RTCEventInterface) {
        print("Not supported in pubnub")
    }

    override fun startListenToEvent(event: RTCEventInterface) {
        pubNubClient.run {
            val mPubNubCallback = object : SubscribeCallback() {
                override fun signal(pubnub: PubNub, pnSignalResult: PNSignalResult) {}

                override fun user(pubnub: PubNub, pnUserResult: PNUserResult) {}

                override fun membership(pubnub: PubNub, pnMembershipResult: PNMembershipResult) {}

                override fun space(pubnub: PubNub, pnSpaceResult: PNSpaceResult) {}


                override fun status(pubnub: PubNub, pnStatus: PNStatus) {

                }

                override fun message(pubnub: PubNub, message: PNMessageResult) {

                    if (listener == null) {
                        throw Exception("Provider call back not implemented")
                    } else {
                        listener!!.onDataReceived(message.channel, "", message.message)
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

    override fun stopListenToEvent(event: RTCEventInterface) {
        TODO("Not yet implemented")
    }

    override fun disconnect() {
        TODO("Not yet implemented")
    }
}
