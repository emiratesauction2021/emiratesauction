package com.ea.emiratesauction.data.datasource.api.model

import java.io.Serializable

interface InternalNetworkErrorInterface : Serializable {
    val status: Boolean?
    val code: Int?
    val message: String?
}