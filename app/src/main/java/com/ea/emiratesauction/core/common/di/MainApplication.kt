package com.ea.emiratesauction.core.common.di

import android.app.Application
import com.ea.emiratesauction.core.network.managers.deeplinsManager.DeepLinkManager
import com.ea.emiratesauction.features.test_toBeDeleted.deeplinks.EmiratesAuctionDestinations
import dagger.hilt.android.HiltAndroidApp
import javax.inject.Inject

@HiltAndroidApp
class MainApplication : Application(){
    @Inject lateinit var deepLinkManager: DeepLinkManager
    override fun onCreate() {
        super.onCreate()

        deepLinkManager.setDestinations(
            EmiratesAuctionDestinations()
        )
    }
}