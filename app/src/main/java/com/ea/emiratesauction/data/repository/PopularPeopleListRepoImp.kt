package com.ea.emiratesauction.data.repository

import com.ea.emiratesauction.core.network.managers.defaultManager.NetworkManager
import com.ea.emiratesauction.features.popularPeoples.domain.model.FailData
import com.ea.emiratesauction.core.network.managers.defaultManager.NetworkProvider
import com.ea.emiratesauction.core.network.result.RequestResult
import com.ea.emiratesauction.features.popularPeoples.domain.model.SuccessData
import com.ea.emiratesauction.features.popularPeoples.domain.repository.PopularPeopleListRepo
import com.ea.emiratesauction.features.popularPeoples.requestTarget.PopularPeoplesRequestTarget
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class PopularPeopleListRepoImp @Inject constructor(private val networkProvider: NetworkManager) : PopularPeopleListRepo {
    override suspend fun getPopularPeopleList(params: PopularPeoplesRequestTarget): RequestResult<SuccessData, FailData> {

        return networkProvider.validate(params).request(params,SuccessData::class.java,FailData::class.java)

    }


}

//
//@Singleton
//class PopularPeopleListRepoImp1 @Inject constructor(private val networkProvider: NetworkProvider) : PopularPeopleListRepo {
//    override suspend fun getPopularPeopleList(params: PopularPeoplesRequestTarget): RequestResult<SuccessData, FailData> {
//
//        return networkProvider.request(params,SuccessData::class.java,FailData::class.java)
//
//    }
//
//
//}