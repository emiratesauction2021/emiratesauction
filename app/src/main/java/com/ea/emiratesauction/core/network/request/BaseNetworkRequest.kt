package com.ea.emiratesauction.core.network.request

import com.ea.emiratesauction.core.constants.network.Host
import com.ea.emiratesauction.core.constants.network.RequestHTTPMethodType
import com.ea.emiratesauction.core.constants.network.NetworkRequestParametersType
import com.ea.emiratesauction.core.constants.network.RequestParameterEncoding

/**
 * this class that will need to be extend to create request configurations
 * @property endPoint is must override it and set api end point url
 * @property parameters is request parameters as Map< Key:String , value:Any > ,
 * if request has no params then pass it empty list
 * @property httpMethod is request type, Get or Post .. etc if request type not passed this will throw exception
 * @property headers by default is initialized with
 * @sample DefaultNetworkRequestHeader it's updatable
 *@property encoding is used for make Standard or Composite
 * @see NetworkRequestParametersType
 * @property baseURL is optional if there is a request that use difference Base Api
 * @property timeOutSeconds is use for Httpclient timeout connect, read, and write
 */
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

