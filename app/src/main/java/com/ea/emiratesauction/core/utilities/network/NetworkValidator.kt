package com.ea.emiratesauction.core.utilities.network

import com.ea.emiratesauction.core.constants.network.NetworkRequestParametersType
import com.ea.emiratesauction.core.constants.network.RequestHTTPMethodType
import com.ea.emiratesauction.core.constants.network.RequestParameterEncoding

class NetworkValidator {
    companion object {
        fun httpMethodValidator(parametersType: NetworkRequestParametersType, method: RequestHTTPMethodType, encoding: RequestParameterEncoding) {
            when (parametersType) {
                is NetworkRequestParametersType.Standard -> {
                    if (encoding == RequestParameterEncoding.URL && method == RequestHTTPMethodType.POST) {
                        throw IllegalArgumentException("You can't use HTTP method POST with URL Encoding")
                    }else if (encoding == RequestParameterEncoding.JSON && method == RequestHTTPMethodType.GET) {
                        throw IllegalArgumentException("You can't use HTTP method GET with JSON Encoding")
                    }
                }
                is NetworkRequestParametersType.Composite -> {
                    if (method == RequestHTTPMethodType.GET) {
                        throw IllegalArgumentException("You can't use HTTP method Get with Composite Parameters")
                    }
                }
            }


        }
    }
}