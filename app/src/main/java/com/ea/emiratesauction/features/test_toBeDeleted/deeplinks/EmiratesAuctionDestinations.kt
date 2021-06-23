package com.ea.emiratesauction.features.test_toBeDeleted.deeplinks

import android.os.Bundle
import com.ea.emiratesauction.R
import com.ea.emiratesauction.core.constants.deeplinks.DeepLinkModel
import com.ea.emiratesauction.core.constants.deeplinks.DeepLinksDestinationsManager
import com.ea.emiratesauction.features.test_toBeDeleted.deeplinks.PlatesFlow.FlowCreator
import com.ea.emiratesauction.features.test_toBeDeleted.network.ui.PopularPeopleListActivity

/**
 * All available destinations which is related to the EA app
 * implements DeepLinksDestinationsManager to fill the list of
 * */

typealias  NavID = R.id
typealias  FLOW = EmiratesAuctionDestinationsType

class EmiratesAuctionDestinations : DeepLinksDestinationsManager {

    override fun getDestinationBackStack(destination: FLOW?): ArrayList<Any> {
        when (destination) {
            FLOW.PLATES -> {
                return arrayListOf(
                    PopularPeopleListActivity::class.java,
                    DeepLinkActivityTestOne::class.java,
                    DeepLinkActivityTestTwo::class.java
                )
            }
            else -> {
            }
        }
        return arrayListOf()
    }

    override fun getNavGraphDestinationBackStackFlow(
        destination: FLOW?,
        deepLinkModel: DeepLinkModel
    ): ArrayList<Pair<Int, Bundle?>>? {
        return FlowCreator().getFlow(
            Pair(destination, deepLinkModel)
        )
    }

}



