package com.ea.emiratesauction.features.popularPeoples.requestTarget

import com.ea.emiratesauction.common.base.domain.RequestTarget
import com.ea.emiratesauction.network_layer.model.RequestMethod
import com.ea.emiratesauction.common.utils.ApiEndPoints
import com.ea.emiratesauction.features.popularPeoples.domain.model.PopularPeopleListResponse
import java.lang.Exception

class PopularPeoplesRequestTarget: RequestTarget<PopularPeopleListResponse>(PopularPeopleListResponse::class.java){


    override val endPointUrl: String
        get() = ApiEndPoints.POPULAR_PEOPLE_ENDPOINT_URL

    override val requestType: RequestMethod
        get() = RequestMethod.GET

    override var requestQueryParams: Map<String, Any> = hashMapOf()
        get() {
            return field
        }
        set(value) {
            if (requestType == RequestMethod.POST)
            field = value
            else
                throw Exception("Please select post method")
        }
    override var requestBodyParams: Map<String, Any> = hashMapOf()
        get() {
            return field
        }
        set(value) {
            field = value
        }

}