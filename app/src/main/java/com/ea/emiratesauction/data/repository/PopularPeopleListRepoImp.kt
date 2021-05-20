package com.ea.emiratesauction.data.repository

import com.ea.emiratesauction.features.popularPeoples.domain.model.FailData
import com.ea.emiratesauction.network_layer.NetworkProviderImp
import com.ea.emiratesauction.network_layer.ResultWrapper
import com.ea.emiratesauction.features.popularPeoples.domain.model.SuccessData
import com.ea.emiratesauction.features.popularPeoples.domain.repository.PopularPeopleListRepo
import com.ea.emiratesauction.features.popularPeoples.requestTarget.PopularPeoplesRequestTarget
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class PopularPeopleListRepoImp @Inject constructor(private val networkManager: NetworkProviderImp) : PopularPeopleListRepo {
    override suspend fun getPopularPeopleList(params: PopularPeoplesRequestTarget): ResultWrapper<SuccessData,FailData> {

        return networkManager.callRequest(params,SuccessData::class.java,FailData::class.java)

    }


}