package com.ea.emiratesauction.features.popularPeoples.domain.repository

import com.ea.emiratesauction.network_layer.ResultWrapper
import com.ea.emiratesauction.features.popularPeoples.domain.model.PopularPeopleListResponse
import com.ea.emiratesauction.features.popularPeoples.requestTarget.PopularPeoplesRequestTarget


interface PopularPeopleListRepo {
    suspend fun getPopularPeopleList(params: PopularPeoplesRequestTarget): ResultWrapper<PopularPeopleListResponse>
}