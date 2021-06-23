package com.ea.emiratesauction.features.test_toBeDeleted.deeplinks.PlatesFlow

import android.os.Bundle
import com.ea.emiratesauction.core.constants.deeplinks.DeepLinkModel
import com.ea.emiratesauction.core.network.managers.deeplinsManager.FlowCreatorInterface
import com.ea.emiratesauction.features.test_toBeDeleted.deeplinks.FLOW
import com.ea.emiratesauction.features.test_toBeDeleted.deeplinks.ScreensName
import javax.inject.Inject

class FlowCreator : FlowCreatorInterface {
    override fun getFlow(screen: Pair<FLOW?, DeepLinkModel>): ArrayList<Pair<Int, Bundle?>> {
        val (screen, model) = screen
        val flowList = arrayListOf<Pair<Int, Bundle?>>()
        when (screen) {
            FLOW.PLATES -> {
                flowList.addAll(
                    arrayListOf(
                        ScreenCreator().create(ScreensName.FragmentOne, model),
                        ScreenCreator().create(ScreensName.FragmentTwo, model),
                    )
                )
            }
            else -> {
            }
        }
        return flowList
    }
}
