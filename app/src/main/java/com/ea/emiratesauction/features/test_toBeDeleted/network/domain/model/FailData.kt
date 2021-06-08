package com.ea.emiratesauction.features.test_toBeDeleted.network.domain.model

import com.ea.emiratesauction.core.network.internalError.interfaces.InternalNetworkErrorInterface

data class FailData(
        override val status: Boolean,
        override val code: Int,
        override val message: String,
        val errorz: String,
        val errorzcode: String
) : InternalNetworkErrorInterface