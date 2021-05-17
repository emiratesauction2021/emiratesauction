package com.ea.emiratesauction.domain.reprositories

import com.ehab.modular.domain.models.PopularPersonDetails

interface PopularPersonDetailsRepo {
    suspend fun getPersonDetails(apiKey : String,personId:Int ,language: String) : PopularPersonDetails

}