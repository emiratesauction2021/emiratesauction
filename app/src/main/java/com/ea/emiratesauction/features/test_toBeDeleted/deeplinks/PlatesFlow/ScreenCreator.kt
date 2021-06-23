package com.ea.emiratesauction.features.test_toBeDeleted.deeplinks.PlatesFlow

import android.os.Bundle
import com.ea.emiratesauction.R
import com.ea.emiratesauction.core.constants.deeplinks.DeepLinkModel
import com.ea.emiratesauction.features.test_toBeDeleted.deeplinks.ScreensName

class ScreenCreator {

    fun create(screensName: ScreensName, model: DeepLinkModel): Pair<Int, Bundle> {
        when (screensName) {
            ScreensName.FragmentOne -> {
                return getFragmentTestOneData(model)
            }
            ScreensName.FragmentTwo -> {
                return getFragmentTestTwoData(model)
            }
        }
        return Pair(0, Bundle())
    }

    fun getFragmentTestOneData(model: DeepLinkModel): Pair<Int, Bundle> {
        val bundleOne = Bundle()
        bundleOne.putString("id", model.id.toString())
        bundleOne.putString("list", model.list)
        return Pair(R.id.fragmentTestOne, bundleOne)
    }

    fun getFragmentTestTwoData(model: DeepLinkModel): Pair<Int, Bundle> {
        val bundleTwo = Bundle()
        bundleTwo.putString("details", model.details)
        return Pair(R.id.fragmentTestTwo, bundleTwo)
    }
}