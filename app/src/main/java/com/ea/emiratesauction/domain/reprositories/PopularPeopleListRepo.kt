package com.ea.emiratesauction.domain.reprositories

import com.ea.emiratesauction.common.network_layer.layer_dependencies.ResultWrapper
import com.ea.emiratesauction.domain.models.PopularPeopleListResponse


interface PopularPeopleListRepo {
    suspend fun getPopularPeopleList(page:Int): ResultWrapper<PopularPeopleListResponse>//apiKey : String ,language: String ,
}