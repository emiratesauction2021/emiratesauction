package com.ea.emiratesauction.core.network.request

import com.ea.emiratesauction.core.constants.network.HTTPHeaderField

class DefaultNetworkRequestHeader:NetworkRequestHeaders{
    override val headers: ArrayList<Pair<String, Any>>
        get() = arrayListOf("Accept-Language" to "en","accept" to "application/json", HTTPHeaderField.CONTENT_TYPE.value to "application/json-patch+json")
    override val auth: ArrayList<Pair<String, Any>>
        get() = arrayListOf("user-token" to "asdasdwd-3do2q30d2k-r32-")

}
