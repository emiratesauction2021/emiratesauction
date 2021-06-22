package com.ea.emiratesauction.core.network.managers.deeplinsManager

import android.net.Uri
import com.ea.emiratesauction.core.constants.deeplinks.DeepLinksDestinationsManager

/**
* DeepLinkManagerInterface it contains all available methods for deeplink manager
* */
interface DeepLinkManagerInterface {
    fun setDeeplinkUrl(url: Uri?)
    fun getLastUrlSegment(): String?
    fun getUrlSegments(): MutableList<String>?
    fun getUrlQueries(): MutableSet<String>?
    fun setDestinations(destination: DeepLinksDestinationsManager)
    fun mangeDestinations()
}