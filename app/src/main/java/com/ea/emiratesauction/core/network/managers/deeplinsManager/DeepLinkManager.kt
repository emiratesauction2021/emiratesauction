package com.ea.emiratesauction.core.network.managers.deeplinsManager

import android.annotation.SuppressLint
import android.content.Context
import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.util.Log
import androidx.core.app.TaskStackBuilder
import androidx.navigation.NavController
import com.ea.emiratesauction.core.constants.deeplinks.DeepLinksDestinationsManager
import com.ea.emiratesauction.core.utilities.deeplinks.DeepLinksUtilities
import com.ea.emiratesauction.features.test_toBeDeleted.deeplinks.EmiratesAuctionDestinationsType
import dagger.hilt.android.qualifiers.ApplicationContext
import javax.inject.Inject

/**
 * Deeplink Manager That will manage deeplink and moving among wanted screen with managing the backstack
 * @param context injected by hilt that will be used to start activities
 * */
@SuppressLint("LogNotTimber")
class DeepLinkManager @Inject constructor(@ApplicationContext val context: Context) :
    DeepLinkManagerInterface {

    /**
     * @property destinationManger it takes the value from the appClass it hold all keys and destinations for the app
     * */
    lateinit var destinationManger: DeepLinksDestinationsManager

    /**
     * @property url that will hold the deeplink data it takes the value from base activity of the app
     * */
    private var url: Uri? = null
    private var navController: NavController? = null


    fun setNavController(navController: NavController) {
        this.navController = navController
    }

    override fun setDestinations(destinationManger: DeepLinksDestinationsManager) {
        this.destinationManger = destinationManger
    }

    override fun setDeeplinkUrl(url: Uri?) {
        this.url = url
        Log.d("DeeplinkManager", "setDeeplinkUrl: $url")
        getLastUrlSegment()
        getUrlSegments()
        getUrlQueries()
        //    mangeDestinations()
    }

    /**
     * It returns the last part of the url if the scheme is like http/abc.com/X/Y/Z
     * */
    override fun getLastUrlSegment(): String? {
        Log.d("DeeplinkManager", "getLastUrlSegment: ${url?.lastPathSegment}")
        return url?.lastPathSegment
    }

    /**
     * It returns the all segments of the url if the scheme is like http/abc.com/X/Y/Z
     * */
    override fun getUrlSegments(): MutableList<String>? {
        Log.d("DeeplinkManager", "getUrlSegments: ${url?.pathSegments}")
        return url?.pathSegments
    }

    /**
     * It returns the all queries of the url if the scheme is like http/abc.com/X?b=1&c=2
     * */
    override fun getUrlQueries(): MutableSet<String>? {
        Log.d("DeeplinkManager", "getUrlQueries: ${url?.queryParameterNames}")
        url?.queryParameterNames?.map {
            Log.d("DeeplinkManager", "getUrlQueries: ${url?.getQueryParameter(it)}")
        }
        return url?.queryParameterNames
    }

    /**
     * It manage all destinations search will be in destinationManger.destinations()
     * to find the deeplink value then use result to get all backstack value and add it to the
     * stack builder with Intent data that exits in url
     * stack is two types :
     * 1-navGraph{
     *  destinationManger.getNavGraphDestinationBack(ENUM)
     * }
     * 2- activities {
     *  destinationManger.getDestinationBackStack(ENUM)
     * }
     */
    override fun mangeDestinations() {

        var segment = getLastUrlSegment()
        if (segment?.matches(Regex(".*\\\\d.*")) == true) {
            segment = getUrlSegments()?.get(getUrlSegments()?.size ?: 0 - 1)
        }
        if (segment?.isNotEmpty() == true) {
            val screen = destinationManger.getNavGraphDestinationBack(
                EmiratesAuctionDestinationsType.values().find {
                    it.destination == segment
                }
            )
            screen.map { id ->
                val bundle = Bundle()
                bundle.putString("PLATES", segment)
                navController?.let {
                    it.navigate(id, bundle)
                }
            }

            //  manageActivityBackStack(segment)
        }

    }

    /**
     * Build the stack if the destinations are activities
     * */
    private fun manageActivityBackStack(segment: String?) {
        val screen = destinationManger.getDestinationBackStack(
            EmiratesAuctionDestinationsType.values().find {
                it.destination == segment
            }
        )
        val stackBuilder = TaskStackBuilder.create(context)
        if (!screen.isNullOrEmpty()) {

            screen.map {
                Log.d("DeeplinkManager", "mangeDestinations: $it")
                stackBuilder.addNextIntent(
                    Intent(
                        context,
                        DeepLinksUtilities.getClassFromPackageName(it.toString())
                    ).putExtra("dd", segment ?: "0")
                )
            }

            stackBuilder.startActivities()
        }
    }
}
