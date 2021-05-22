package com.ea.emiratesauction.features.popularPeoples.domain.repository

import com.ea.emiratesauction.features.popularPeoples.domain.model.FailData
import com.ea.emiratesauction.core.network.result.RequestResult
import com.ea.emiratesauction.features.popularPeoples.domain.model.SuccessData
import com.ea.emiratesauction.features.popularPeoples.requestTarget.PopularPeoplesRequestTarget


interface PopularPeopleListRepo {
    suspend fun getPopularPeopleList(params: PopularPeoplesRequestTarget): RequestResult<SuccessData, FailData>
}