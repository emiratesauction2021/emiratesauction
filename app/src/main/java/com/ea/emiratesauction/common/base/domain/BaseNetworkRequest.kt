package com.ea.emiratesauction.common.base.domain

import com.ea.emiratesauction.network_layer.model.BaseUrlType
import com.ea.emiratesauction.network_layer.model.RequestMethod
import com.ea.emiratesauction.common.utils.ApiEndPoints
import java.lang.Exception

abstract class BaseNetworkRequest {

    open val baseUrl: BaseUrlType = BaseUrlType.MOCK
    abstract val endPointUrl: String
    abstract val requestType: RequestMethod
    abstract var requestQueryParams: Map<String, Any>
    open var requestBodyParams: Map<String, Any> = hashMapOf()
        get() {
            return field
        }
        set(value) {
            if (requestType == RequestMethod.POST)
                field = value
            else
                throw Exception("Please select post method")
        }

    open val headersMap: Map<String, Any> = ApiEndPoints.headersMap

}