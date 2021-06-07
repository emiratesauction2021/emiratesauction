package com.ea.emiratesauction.core.network.internalError

import java.io.Serializable

/**
 * The internal network error interface which any internal error in the code has to implement
 */
interface InternalNetworkErrorInterface : Serializable {
    /**
     * The status of the network request if it's successful -> true or failed -> false
     */
    val status: Boolean?

    /**
     * The internal error code
     */
    val code: Int?

    /**
     * The descriptive message of the error
     */
    val message: String?
}