package com.ea.emiratesauction.core.network.request.networkRequest

import com.ea.emiratesauction.core.constants.network.hosts.Host
import com.ea.emiratesauction.core.constants.network.httpMethods.RequestHTTPMethodType
import com.ea.emiratesauction.core.constants.network.encodings.RequestParameterEncoding
import com.ea.emiratesauction.core.constants.network.parameters.NetworkRequestParametersType
import com.ea.emiratesauction.core.network.request.headers.defaultHeaders.DefaultNetworkRequestHeader
import com.ea.emiratesauction.core.network.request.headers.normal.NetworkRequestHeaders

/**
 * The abstract class of the network request which encapsulate all the required information about a specific request to be used by the network manager
 *
 * Some of the its variable will have default implementation which you can override if needed such as headers, encoding ...etc
 *
 */
abstract class BaseNetworkRequest {
    /**
     * The endpoint of the request
     */
    abstract var endPoint: String

    /**
     * The parameters of the request whether it's query params or a combination between query params and body params
     */
    abstract var parameters: NetworkRequestParametersType

    /**
     * The HTTP method of the request
     */
    abstract var httpMethod: RequestHTTPMethodType

    /**
     * The headers of the request, by default it's DefaultNetworkRequestHeader
     */
    open var headers: NetworkRequestHeaders = DefaultNetworkRequestHeader()

    /**
     * The encoding of the request, by default it's URL if the HTTP method is GET and JSON if the HTTP method is POST
     */
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

    /**
     * The base URL of the request, by default it's Host.DEFAULT_BASE
     */
    open var baseURL: Host = Host.DEFAULT_BASE

    /**
     * The timeouts for the request in seconds, by default it's 20 seconds
     *
     * Note: The same timeout value is used for: write timeout, read timeout, and connection timeout
     */
    open var timeOutSeconds: Long = 20

}