package com.ea.emiratesauction.common.base.domain

import com.ea.emiratesauction.network_layer.model.BaseUrlType
import com.ea.emiratesauction.network_layer.model.RequestMethod
import com.ea.emiratesauction.common.utils.ApiEndPoints

abstract class RequestTarget<T>(val responseClassType: Class<T>) {

    open val baseUrl: BaseUrlType = BaseUrlType.DEVELOP
    abstract val endPointUrl: String
    abstract val requestType: RequestMethod // = RequestMethod.NONE
    abstract var requestQueryParams: Map<String, Any>
    abstract var requestBodyParams: Map<String, Any>
    open val headersMap: Map<String, Any> = ApiEndPoints.headersMap

}