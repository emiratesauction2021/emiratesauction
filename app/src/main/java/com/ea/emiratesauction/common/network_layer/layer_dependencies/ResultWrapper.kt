package com.ea.emiratesauction.common.network_layer.layer_dependencies

sealed class ResultWrapper<out T>{
    data class Success<out T>(val value: T) : ResultWrapper<T>()
    data class CustomError(val code: Int? = null, val error: String? = null) : ResultWrapper<Nothing>()
    object NetworkError : ResultWrapper<Nothing>()
    object ServerError : ResultWrapper<Nothing>()
    object AuthorizationError : ResultWrapper<Nothing>()
}
