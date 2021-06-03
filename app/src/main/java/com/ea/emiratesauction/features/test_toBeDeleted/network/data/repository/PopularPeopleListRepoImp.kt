package com.ea.emiratesauction.features.test_toBeDeleted.network.data.repository

import com.ea.emiratesauction.core.network.managers.defaultManager.NetworkManager
import com.ea.emiratesauction.features.test_toBeDeleted.network.domain.model.FailData
import com.ea.emiratesauction.core.network.result.RequestResult
import com.ea.emiratesauction.features.test_toBeDeleted.network.domain.model.SuccessData
import com.ea.emiratesauction.features.test_toBeDeleted.network.domain.repository.PopularPeopleListRepo
import com.ea.emiratesauction.features.test_toBeDeleted.network.requestTarget.PopularPeoplesRequestTarget
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class PopularPeopleListRepoImp @Inject constructor(private val networkProvider: NetworkManager) : PopularPeopleListRepo {
    override suspend fun getPopularPeopleList(params: PopularPeoplesRequestTarget): RequestResult<SuccessData, FailData> {
        return networkProvider.validate(params).request(params,SuccessData::class.java,FailData::class.java)
    }
}