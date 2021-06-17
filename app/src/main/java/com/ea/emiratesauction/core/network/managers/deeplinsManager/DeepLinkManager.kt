package com.ea.emiratesauction.core.network.managers.deeplinsManager

import android.annotation.SuppressLint
import android.content.Context
import android.content.Intent
import android.net.Uri
import android.util.Log
import androidx.core.app.TaskStackBuilder
import com.ea.emiratesauction.core.constants.deeplinks.DeepLinksDestinationsManager
import com.ea.emiratesauction.core.utilities.deeplinks.DeepLinksUtilities
import com.ea.emiratesauction.features.test_toBeDeleted.deeplinks.EmiratesAuctionDestinationsType
import com.ea.emiratesauction.features.test_toBeDeleted.deeplinks.PlatesDestination
import dagger.hilt.android.qualifiers.ApplicationContext
import javax.inject.Inject

@SuppressLint("LogNotTimber")
class DeepLinkManager @Inject constructor(@ApplicationContext val context: Context) :
    DeepLinkManagerInterface {
    private var url: Uri? = null
    lateinit var destination: DeepLinksDestinationsManager

    override fun setDestinations(destination: DeepLinksDestinationsManager) {
        this.destination = destination
    }

    override fun setDeeplinkUrl(url: Uri?) {
        this.url = url
        Log.d("DeeplinkManager", "setDeeplinkUrl: $url")
        getLastUrlSegment()
        getUrlSegments()
        getUrlQueries()
        mangeDestinations()
    }

    override fun getLastUrlSegment(): String? {
        Log.d("DeeplinkManager", "getLastUrlSegment: ${url?.lastPathSegment}")
        return url?.lastPathSegment
    }


    override fun getUrlSegments(): MutableList<String>? {
        Log.d("DeeplinkManager", "getUrlSegments: ${url?.pathSegments}")
        return url?.pathSegments
    }

    override fun getUrlQueries(): MutableSet<String>? {
        Log.d("DeeplinkManager", "getUrlQueries: ${url?.queryParameterNames}")
        url?.queryParameterNames?.map {
            Log.d("DeeplinkManager", "getUrlQueries: ${url?.getQueryParameter(it)}")
        }
        return url?.queryParameterNames
    }

    override fun mangeDestinations() {

        var segment = getLastUrlSegment()
        if (segment?.matches(Regex(".*\\\\d.*")) == true) {
            segment = getUrlSegments()?.get(getUrlSegments()?.size ?: 0 - 1)
        }
        if (segment?.isNotEmpty() == true) {
            val screen = destination.destinations.find {
                it.destinationClass.contains(segment)
            }
            if (screen != null) {
                val stackBuilder = TaskStackBuilder.create(context)
                screen.backStack.map {
                    Log.d("DeeplinkManager", "mangeDestinations: $it")
                    stackBuilder.addNextIntent(
                        Intent(
                            context,
                            DeepLinksUtilities.getClassFromPackageName(it.toString())
                        ).putExtra(EmiratesAuctionDestinationsType.PLATES.name, segment ?: "0")
                    )
                }
                stackBuilder.startActivities()
            }
        }
    }

    override fun navigateToActivity(data: Intent) {

    }

    override fun navigateToFragment(data: Intent) {
    }


}
