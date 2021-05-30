package com.ea.emiratesauction.core.constants.network

typealias NetworkRequestParameters = Map<String, Any>

/**
 * if request type is get or post with body parameters then use
 * @property Standard
 * if request type is post with parameters in body and query will need to use
 * @property Composite
 */
sealed class NetworkRequestParametersType{
    data class Standard(var params: NetworkRequestParameters) : NetworkRequestParametersType()
    data class Composite(var bodyParams: NetworkRequestParameters, var queryParams: NetworkRequestParameters) : NetworkRequestParametersType()
}