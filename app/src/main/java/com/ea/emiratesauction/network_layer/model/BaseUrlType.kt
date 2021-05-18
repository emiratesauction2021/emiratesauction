package com.ea.emiratesauction.network_layer.model

import com.ea.emiratesauction.common.utils.ApiEndPoints

enum class BaseUrlType(val baseUrl:String) {
    LIVE(ApiEndPoints.BASE_API_URL),
    DEVELOP(ApiEndPoints.BASE_API_URL),
    MOCK(ApiEndPoints.BASE_API_URL)
}