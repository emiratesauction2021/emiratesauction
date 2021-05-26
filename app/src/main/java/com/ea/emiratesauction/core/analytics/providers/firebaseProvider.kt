package com.ea.emiratesauction.core.analytics.providers

import com.ea.emiratesauction.core.analytics.event.AnalyticsEventInterface
import com.ea.emiratesauction.core.analytics.profile.ProfileIdentificationInterface
import com.ea.emiratesauction.core.analytics.properties.BasePropertiesInterface
import com.ea.emiratesauction.core.analytics.properties.ProfilePropertiesInterface
import com.ea.emiratesauction.core.analytics.screen.AnalyticsScreenInterface

class firebaseProvider: AnalyticsProviderInterface {

    override var providerID: AnalyticsProviderIdentifier
        get() = TODO("Not yet implemented")
        set(value) {}
    override var defaultEventProperties: BasePropertiesInterface
        get() = TODO("Not yet implemented")
        set(value) {}
    override var defaultScreenProperties: BasePropertiesInterface
        get() = TODO("Not yet implemented")
        set(value) {}

    override fun logEvent(event: AnalyticsEventInterface) {
        TODO("Not yet implemented")
    }

    override fun trackScreen(screen: AnalyticsScreenInterface) {
        TODO("Not yet implemented")
    }

    override fun setupProfileIdentification(profileID: ProfileIdentificationInterface) {
        TODO("Not yet implemented")
    }

    override fun setProfileProperties(profileIdentification: ProfilePropertiesInterface) {
        TODO("Not yet implemented")
    }

}