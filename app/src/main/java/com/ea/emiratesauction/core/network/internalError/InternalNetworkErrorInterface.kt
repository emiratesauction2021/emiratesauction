package com.ea.emiratesauction.core.network.internalError

import java.io.Serializable

interface InternalNetworkErrorInterface : Serializable {
    val status: Boolean?
    val code: Int?
    val message: String?
}