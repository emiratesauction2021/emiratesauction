package com.ea.emiratesauction.core.analytics.providers

import com.ea.emiratesauction.core.analytics.UserIdentification
import com.ea.emiratesauction.core.analytics.event.AnalyticsEventInterface
import com.ea.emiratesauction.core.analytics.screen.AnalyticsScreenInterface
import javax.inject.Inject

class firebaseProvider(
) : AnalyticsProviderInterface {
    override var provider: AnalyticsProvider
        get() = TODO("Not yet implemented")
        set(value) {}
    override var defaultParameters: Map<String, Any>
        get() = TODO("Not yet implemented")
        set(value) {}


    override fun setDefaultParameters(event: AnalyticsEventInterface) {
        TODO("Not yet implemented")
    }

    override fun logEvent(event: AnalyticsEventInterface) {
        TODO("Not yet implemented")
    }

    override fun setUpIdentification(userIdentification: UserIdentification) {
        TODO("Not yet implemented")
    }

    override fun trackScreen(screen: AnalyticsScreenInterface) {
        TODO("Not yet implemented")
    }


}