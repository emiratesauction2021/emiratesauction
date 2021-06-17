package com.ea.emiratesauction.core.network.managers.deeplinsManager

import android.content.Intent

interface DeepLinkManagerInterface {
    fun navigateToActivity(data: Intent)
    fun navigateToFragment(data: Intent)
}