package com.ea.emiratesauction.features.test_toBeDeleted.deeplinks

import com.ea.emiratesauction.core.constants.deeplinks.DeepLinksDestinationModel
import com.ea.emiratesauction.core.constants.deeplinks.DeepLinksDestinationsManager
import com.ea.emiratesauction.features.test_toBeDeleted.network.ui.PopularPeopleListActivity

class EmiratesAuctionDestinations : DeepLinksDestinationsManager {
    override val destinations: ArrayList<DeepLinksDestinationModel>
        get() = arrayListOf(PlatesDestination())
}

class PlatesDestination : DeepLinksDestinationModel {
    override val destinationClass: String
        get() = EmiratesAuctionDestinationsType.PLATES.destination
    override val backStack: ArrayList<Any>
        get() = arrayListOf(
            PopularPeopleListActivity::class.java,
            DeepLinkActivityTestOne::class.java,
            DeepLinkActivityTestTwo::class.java
        )

}




