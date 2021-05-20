package com.ea.emiratesauction.features.popularPeoples.domain.model

import com.ea.emiratesauction.data.datasource.api.model.InternalNetworkErrorInterface

data class FailData(
        override val status: Boolean,
        override val code: Int,
        override val message: String,
        val errorz: String,
        val errorzcode: String
) : InternalNetworkErrorInterface