package com.ea.emiratesauction.features.popularPeoples.domain.model

import com.ea.emiratesauction.core.network.internalError.InternalNetworkErrorInterface

data class FailData(
        override val status: Boolean,
        override val code: Int,
        override val message: String,
        val errorz: String,
        val errorzcode: String
) : InternalNetworkErrorInterface