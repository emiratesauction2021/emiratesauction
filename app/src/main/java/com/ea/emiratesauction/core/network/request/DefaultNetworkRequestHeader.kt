package com.ea.emiratesauction.core.network.request

import com.ea.emiratesauction.core.constants.network.ContentType
import com.ea.emiratesauction.core.constants.network.HTTPHeaderField

/**
 * there is tow type of headers
 * @property headers is for normal headers like Content-Type, Accept-Language, ... etc
 * @property auth is for Authorization headers if your request has Authorization headers then you will base those headers in auth
 *
 */
class DefaultNetworkRequestHeader:NetworkRequestHeaders{
    override val headers: ArrayList<Pair<String, Any>>
        get() = arrayListOf(HTTPHeaderField.CONTENT_TYPE.value to ContentType.JSON.value)
    override val auth: ArrayList<Pair<String, Any>>
        get() = arrayListOf()

}
