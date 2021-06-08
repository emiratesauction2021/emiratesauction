package com.ea.emiratesauction.core.network.request.headers.authorization

/**
 * The Authorization methods and variables used by a request
 */
interface NetworkRequestAuthorization{
    /**
     * The Authorization headers used by a request
     */
    val auth : ArrayList<Pair<String, Any>>
}