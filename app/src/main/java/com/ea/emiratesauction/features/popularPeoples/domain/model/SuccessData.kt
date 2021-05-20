package com.ea.emiratesauction.features.popularPeoples.domain.model

import com.ea.emiratesauction.data.datasource.api.model.InternalNetworkErrorInterface

data class SuccessData(
        override val status: Boolean,
        override val code: Int,
        override val message: String,
        val list: List<PopularPerson>
) : InternalNetworkErrorInterface