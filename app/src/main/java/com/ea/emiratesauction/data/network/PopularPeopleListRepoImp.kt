package com.ea.emiratesauction.data.network

import com.ea.emiratesauction.common.network_layer.layer_dependencies.RequestMethod
import com.ea.emiratesauction.common.network_layer.layer_dependencies.NetworkManagerImp
import com.ea.emiratesauction.common.network_layer.layer_dependencies.RequestProvider
import com.ea.emiratesauction.common.network_layer.layer_dependencies.ResultWrapper
import com.ea.emiratesauction.common.utils.AppConstants
import com.ea.emiratesauction.domain.models.PopularPeopleListResponse
import com.ea.emiratesauction.domain.reprositories.PopularPeopleListRepo
import com.ea.emiratesauction.common.utils.ApiEndPoints
import java.net.Authenticator
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class PopularPeopleListRepoImp @Inject constructor(private val networkManager: NetworkManagerImp) : PopularPeopleListRepo {
    override suspend fun getPopularPeopleList(
            page: Int
    ): ResultWrapper<PopularPeopleListResponse> {

        val requestParams = hashMapOf<String, Any>()
        requestParams["api_key"] = ApiEndPoints.API_KEY
        requestParams["language"] = AppConstants.LANGUAGE_US
        requestParams["page"] = page
        return networkManager.callRequest(RequestProvider(PopularPeopleListResponse::class.java).apply {

            requestType = RequestMethod.GET
            EndPointUrl = ApiEndPoints.POPULAR_PEOPLE_ENDPOINT_URL
            this.requestParams = requestParams
            
        })

    }
}