package com.ea.emiratesauction.features.popularPeoples.requestTarget

import com.ea.emiratesauction.common.base.domain.BaseNetworkRequest
import com.ea.emiratesauction.network_layer.model.RequestMethod
import com.ea.emiratesauction.common.utils.ApiEndPoints
import com.ea.emiratesauction.network_layer.model.BaseUrlType
import java.lang.Exception

class PopularPeoplesRequestTarget: BaseNetworkRequest(){
    override val baseUrl: BaseUrlType
        get() = BaseUrlType.MOCK

    override val endPointUrl: String
        get() = ApiEndPoints.MOCK_POPULAR_PEOPLE_ENDPOINT_URL

    override val requestType: RequestMethod
        get() = RequestMethod.POST

    override var requestQueryParams: Map<String, Any> = hashMapOf()

    override var requestBodyParams: Map<String, Any> = hashMapOf()

}