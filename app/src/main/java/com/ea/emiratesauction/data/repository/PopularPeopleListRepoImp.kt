package com.ea.emiratesauction.data.repository

import com.ea.emiratesauction.network_layer.NetworkManagerImp
import com.ea.emiratesauction.network_layer.ResultWrapper
import com.ea.emiratesauction.features.popularPeoples.domain.model.PopularPeopleListResponse
import com.ea.emiratesauction.features.popularPeoples.domain.repository.PopularPeopleListRepo
import com.ea.emiratesauction.features.popularPeoples.requestTarget.PopularPeoplesRequestTarget
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class PopularPeopleListRepoImp @Inject constructor(private val networkManager: NetworkManagerImp) : PopularPeopleListRepo {

    override suspend fun getPopularPeopleList(
            params: PopularPeoplesRequestTarget
    ): ResultWrapper<PopularPeopleListResponse> {

        return networkManager.callRequest(params)

    }
}