package com.ea.emiratesauction.core.utilities.network

import com.ea.emiratesauction.core.constants.network.httpMethods.RequestHTTPMethodType
import com.ea.emiratesauction.core.constants.network.encodings.RequestParameterEncoding
import com.ea.emiratesauction.core.constants.network.parameters.NetworkRequestParametersType
import com.ea.emiratesauction.core.network.request.networkRequest.BaseNetworkRequest

/**
 * The class which is encapsulating most of the network validations
 */
class NetworkValidator {
    companion object {
        /**
         * Validates the http comon usage of a specific request
         *
         * @param parametersType The type of the parameter
         *
         * @param method The HTTP method of the request
         *
         * @param encoding The encoding which will be used to encode the parameters
         *
         * @throws Exception if the passed values violates the proper construction of the request
         */
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