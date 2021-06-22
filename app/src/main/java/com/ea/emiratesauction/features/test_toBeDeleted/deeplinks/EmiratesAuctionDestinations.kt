package com.ea.emiratesauction.features.test_toBeDeleted.deeplinks

import com.ea.emiratesauction.R
import com.ea.emiratesauction.core.constants.deeplinks.DeepLinksDestinationsManager
import com.ea.emiratesauction.features.test_toBeDeleted.network.ui.PopularPeopleListActivity

/**
* All available destinations which is related to the EA app
* implements DeepLinksDestinationsManager to fill the list of
* */

typealias  NavID = R.id
typealias  ScreenName = EmiratesAuctionDestinationsType

class EmiratesAuctionDestinations : DeepLinksDestinationsManager {

    override fun getDestinationBackStack(destination: ScreenName?): ArrayList<Any> {
        when (destination) {
            ScreenName.PLATES -> {
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

    override fun getNavGraphDestinationBack(destination: ScreenName?): ArrayList<Int> {
        when (destination) {
            ScreenName.PLATES -> {
                return arrayListOf(
                    NavID.fragmentTestOne,
                    NavID.fragmentTestTwo,
                    NavID.pupularPeopleListFragment,
                    NavID.fragmentTestOne,
                    NavID.pupularPeopleListFragment,
                    NavID.fragmentTestTwo,
                    NavID.pupularPeopleListFragment,
                    NavID.fragmentTestOne,
                    NavID.pupularPeopleListFragment,
                    NavID.fragmentTestOne,
                )
            }
            else -> {
            }
        }
        return arrayListOf()
    }
}



