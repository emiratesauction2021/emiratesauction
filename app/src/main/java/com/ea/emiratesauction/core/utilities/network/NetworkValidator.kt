package com.ea.emiratesauction.core.utilities.network

import com.ea.emiratesauction.core.constants.network.NetworkRequestParametersType
import com.ea.emiratesauction.core.constants.network.httpMethods.RequestHTTPMethodType
import com.ea.emiratesauction.core.constants.network.encodings.RequestParameterEncoding
import com.ea.emiratesauction.core.network.request.networkRequest.BaseNetworkRequest

/**
 * this class is used to make check on request Target data
 * @see BaseNetworkRequest
 */
class NetworkValidator {
    companion object {
        fun httpMethodValidator(parametersType: NetworkRequestParametersType, method: RequestHTTPMethodType, encoding: RequestParameterEncoding) {
            when (parametersType) {
                is NetworkRequestParametersType.Standard -> {
                    if (encoding == RequestParameterEncoding.URL && method == RequestHTTPMethodType.POST) {
                        throw NetworkExceptions.EncodingMethodException("You can't use HTTP method POST with URL Encoding")
                    }else if (encoding == RequestParameterEncoding.JSON && method == RequestHTTPMethodType.GET) {
                        throw NetworkExceptions.EncodingMethodException("You can't use HTTP method GET with JSON Encoding")
                    }
                }
                is NetworkRequestParametersType.Composite -> {
                    if (method == RequestHTTPMethodType.GET) {
                        throw NetworkExceptions.CompositeMethodException("You can't use HTTP method Get with Composite Parameters")
                    }
                }
            }


        }
    }
}