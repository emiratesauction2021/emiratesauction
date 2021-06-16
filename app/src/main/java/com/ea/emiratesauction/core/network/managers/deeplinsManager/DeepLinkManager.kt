package com.ea.emiratesauction.core.network.managers.deeplinsManager

import android.content.Context
import android.content.Intent
import android.util.Log
import dagger.hilt.android.qualifiers.ApplicationContext
import java.util.logging.Logger
import javax.inject.Inject

class DeepLinkManager @Inject constructor(@ApplicationContext val context: Context) :
    DeepLinkManagerInterface {
    private var url: String = ""

    fun setDeeplinkUrl(url: String) {
        this.url = url
        Log.d("DeeplinkManager", "setDeeplinkUrl: $url")
    }

    private fun getUrlSegments() {

    }

    private fun getUrlQueries() {

    }

    override fun navigateToActivity(data: Intent) {

    }

    override fun navigateToFragment(data: Intent) {
    }
}