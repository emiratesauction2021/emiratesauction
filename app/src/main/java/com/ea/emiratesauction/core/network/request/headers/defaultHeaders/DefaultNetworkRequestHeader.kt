package com.ea.emiratesauction.core.network.request.headers.defaultHeaders

import com.ea.emiratesauction.core.constants.network.ContentType
import com.ea.emiratesauction.core.constants.network.HTTPHeaderField
import com.ea.emiratesauction.core.network.request.headers.normal.NetworkRequestHeaders

/**
 * The default headers which will be used by any request initially, which you can update or override
 */
class DefaultNetworkRequestHeader: NetworkRequestHeaders {
    override val headers: Map<String, Any>
        get() = mapOf(HTTPHeaderField.CONTENT_TYPE.value to ContentType.JSON.value)
    override val auth: Map<String, Any>
        get() = mapOf()

}
