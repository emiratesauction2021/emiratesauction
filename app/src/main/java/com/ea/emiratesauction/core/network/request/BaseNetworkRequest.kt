package com.ea.emiratesauction.core.network.request

import androidx.datastore.preferences.protobuf.Api
import com.ea.emiratesauction.common.utils.ApiEndPoints
import com.ea.emiratesauction.core.constants.network.RequestHTTPMethodType
import com.ea.emiratesauction.core.constants.network.NetworkRequestParametersType
import com.ea.emiratesauction.core.constants.network.RequestParameterEncoding

abstract class BaseNetworkRequest {

    abstract var endPoint: String
    abstract var parameters: NetworkRequestParametersType
    abstract var httpMethod: RequestHTTPMethodType

    open var headers:NetworkRequestHeaders = DefaultNetworkRequestHeader()

    open var encoding: RequestParameterEncoding = RequestParameterEncoding.JSON
        get() {
            return when (httpMethod) {
                RequestHTTPMethodType.GET -> {
                    RequestParameterEncoding.URL
                }

                else -> {
                    RequestParameterEncoding.JSON
                }
            }
        }
    open var baseURL: Host = Host.DEFAULT_BASE
    open var timeOutSeconds: Long = 20

}

