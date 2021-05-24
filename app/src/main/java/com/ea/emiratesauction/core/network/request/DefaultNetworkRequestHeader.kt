package com.ea.emiratesauction.core.network.request

import com.ea.emiratesauction.core.constants.network.ContentType
import com.ea.emiratesauction.core.constants.network.HTTPHeaderField

class DefaultNetworkRequestHeader:NetworkRequestHeaders{
    override val headers: ArrayList<Pair<String, Any>>
        get() = arrayListOf(HTTPHeaderField.CONTENT_TYPE.value to ContentType.JSON.value)
    override val auth: ArrayList<Pair<String, Any>>
        get() = arrayListOf()

}
