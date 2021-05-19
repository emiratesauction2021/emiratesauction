package com.ea.emiratesauction.network_layer

import com.ea.emiratesauction.data.datasource.api.model.ApiErrorModel

sealed class ResultWrapper<out T>{
    data class Success<out T>(val value: T?) : ResultWrapper<T>()

    data class Fail(val error: ApiErrorModel) : ResultWrapper<Nothing>()
}
