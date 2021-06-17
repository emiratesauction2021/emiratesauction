package com.ea.emiratesauction.core.network.managers.deeplinsManager

import android.content.Intent
import android.net.Uri
import com.ea.emiratesauction.core.constants.deeplinks.DeepLinksDestinationsManager

interface DeepLinkManagerInterface {
    fun setDestinations(destination: DeepLinksDestinationsManager)
    fun setDeeplinkUrl(url: Uri?)
    fun getLastUrlSegment(): String?
    fun getUrlSegments(): MutableList<String>?
    fun getUrlQueries(): MutableSet<String>?
    fun mangeDestinations()
    fun navigateToActivity(data: Intent)
    fun navigateToFragment(data: Intent)
}