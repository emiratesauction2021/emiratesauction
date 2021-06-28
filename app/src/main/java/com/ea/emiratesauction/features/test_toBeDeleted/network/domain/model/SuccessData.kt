package com.ea.emiratesauction.features.test_toBeDeleted.network.domain.model

import com.ea.emiratesauction.core.network.internalError.interfaces.InternalNetworkErrorInterface

data class SuccessData(
        override val status: Boolean,
        override val code: Int,
        override val message: String,
        val list: List<PopularPerson>
) : InternalNetworkErrorInterface