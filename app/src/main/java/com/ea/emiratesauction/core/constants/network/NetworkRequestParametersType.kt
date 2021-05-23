package com.ea.emiratesauction.core.constants.network

typealias NetworkRequestParameters = Map<String, Any>

sealed class NetworkRequestParametersType{
    data class Standard(var params: NetworkRequestParameters) : NetworkRequestParametersType()
    data class Composite(var bodyParams: NetworkRequestParameters, var queryParams: NetworkRequestParameters) : NetworkRequestParametersType()
}