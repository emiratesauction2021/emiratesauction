package com.ea.emiratesauction.core.constants.deeplinks

import com.ea.emiratesauction.features.test_toBeDeleted.deeplinks.EmiratesAuctionDestinationsType

/**
 * DeepLinksDestinationsManager  has arraylist of DeepLinksDestinationModel that contains
 * all keys available for the deeplink with backstack option for each screen
 * */
interface DeepLinksDestinationsManager {
    /**
     * Build the Stack For Activities
     * @param destination it an enum which hold all key for screens
     * @return arrayList of classes names base on sent key
     */
    fun getDestinationBackStack(destination: EmiratesAuctionDestinationsType?): ArrayList<Any>

    /**
     * Build the Stack For NavGraph
     * @param destination it an enum which hold all key for screens
     * @return arrayList of Fragments Ids base on sent key
     */
    fun getNavGraphDestinationBack(destination: EmiratesAuctionDestinationsType?): ArrayList<Int>
}