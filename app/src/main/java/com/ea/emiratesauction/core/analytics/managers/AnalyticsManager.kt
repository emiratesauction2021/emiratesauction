package com.ea.emiratesauction.core.analytics.managers

import com.ea.emiratesauction.core.analytics.UserIdentification
import com.ea.emiratesauction.core.analytics.event.AnalyticsEventInterface
import com.ea.emiratesauction.core.analytics.providers.AnalyticsProvider
import com.ea.emiratesauction.core.analytics.providers.AnalyticsProviderInterface
import com.ea.emiratesauction.core.analytics.screen.AnalyticsScreenInterface
import javax.inject.Inject

class AnalyticsManager @Inject constructor(private val providers: Map<AnalyticsProvider, AnalyticsProviderInterface>) :
    AnalyticsManagerInterface {

    override fun setDefaultParameters(event: AnalyticsEventInterface) {
        for (providersName in event.providers) {
            providers.get(providersName)!!.setDefaultParameters(event)
        }
    }

    override fun logEvent(event: AnalyticsEventInterface) {
        for (providersName in event.providers) {
            providers.get(providersName)!!.logEvent(event)
        }
    }

    override fun setUpIdentification(userIdentification: UserIdentification) {
        TODO("Not yet implemented")
    }

    override fun trackScreen(screen: AnalyticsScreenInterface) {
        TODO("Not yet implemented")
    }


}