package com.ea.emiratesauction.core.network.request

import com.ea.emiratesauction.BuildConfig
import com.ea.emiratesauction.common.utils.ApiEndPoints

enum class Host(val baseUrl:String) {
    DEFAULT_BASE(BuildConfig.BASE_URL)
}