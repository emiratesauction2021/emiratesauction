package com.ea.emiratesauction.domain.models

import java.io.Serializable

data class PopularPeopleListResponse (
    val page: Int,
    val total_results: Int,
    val total_pages: Int,
     val results: List<PopularPerson>?=null
):Serializable{


}