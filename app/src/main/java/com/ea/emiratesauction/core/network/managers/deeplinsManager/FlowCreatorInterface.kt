package com.ea.emiratesauction.core.network.managers.deeplinsManager

import android.os.Bundle
import com.ea.emiratesauction.core.constants.deeplinks.DeepLinkModel
import com.ea.emiratesauction.features.test_toBeDeleted.deeplinks.FLOW


interface FlowCreatorInterface {
    fun getFlow(
        screen: Pair<FLOW?, DeepLinkModel>
    ): ArrayList<Pair<Int, Bundle?>>
}