package com.ea.emiratesauction.features.test_toBeDeleted.network.domain.repository

import com.ea.emiratesauction.features.test_toBeDeleted.network.domain.model.FailData
import com.ea.emiratesauction.core.network.result.RequestResult
import com.ea.emiratesauction.features.test_toBeDeleted.network.domain.model.SuccessData
import com.ea.emiratesauction.features.test_toBeDeleted.network.requestTarget.PopularPeoplesRequestTarget


interface PopularPeopleListRepo {
    suspend fun getPopularPeopleList(params: PopularPeoplesRequestTarget): RequestResult<SuccessData, FailData>
}