package com.ea.emiratesauction.core.network.request.headers.normal

import com.ea.emiratesauction.core.network.request.headers.authorization.NetworkRequestAuthorization

/**
 * The headers methods and variables used by a request
 */
interface NetworkRequestHeaders: NetworkRequestAuthorization {
    /**
     * The headers used by a request
     */
    val headers:Map<String, Any>

    /**
     * Retrieves all the headers combined together - Auth Headers & HTTP Headers
     *
     * @return A map with all the headers combined
     */
    fun getAllHeaders():Map<String, Any>{
        val allHeaders = HashMap<String, Any>()
        allHeaders.putAll(headers)
        allHeaders.putAll(auth)
        return allHeaders
    }
}

