package com.ea.emiratesauction.features.test_toBeDeleted.domain.repository

import com.ea.emiratesauction.features.test_toBeDeleted.domain.model.FailData
import com.ea.emiratesauction.core.network.result.RequestResult
import com.ea.emiratesauction.features.test_toBeDeleted.domain.model.SuccessData
import com.ea.emiratesauction.features.test_toBeDeleted.requestTarget.PopularPeoplesRequestTarget


interface PopularPeopleListRepo {
    suspend fun getPopularPeopleList(params: PopularPeoplesRequestTarget): RequestResult<SuccessData, FailData>
}