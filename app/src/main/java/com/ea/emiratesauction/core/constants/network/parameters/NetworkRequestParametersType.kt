package com.ea.emiratesauction.core.constants.network.parameters

typealias NetworkRequestParameters = Map<String, Any>

/**
 * Defining the network request parameters types, whether it's query params or a combination between query params and body params
 *
 * |------------------------------------------|
 *
 * Standard should be used if the parameters is either query or body within the same request
 *
 * Ex: URL -> "http://base.com" and params is -> ["a" = "aaa", "b" = 2]
 *
 * * Result in case of get request -> "http://base.com?a=aaa&b=2"
 *
 * * Result in case of post request -> "http://base.com" & body params is -> {"a":"aaa", "b":2}
 *
 * |------------------------------------------|
 *
 * Composite should be used if you have to combine both query and body params within the same request
 *
 * Ex: URL -> "http://base.com" and queryParams is -> ["a" = "aaa", "b" = 2] & bodyParams is-> ["c" = "ccc", "d" = 3]
 *
 * * Result in case of get request -> "http://base.com?a=aaa&b=2" & body is nothing as it's a get request
 *
 * * Result in case of post request -> "http://base.com?a=aaa&b=2" & body params is -> {"c":"ccc", "b":3}
 *
 */
sealed class NetworkRequestParametersType{
    /**
     * Standard should be used if the parameters is either query or body within the same request
     *
     * Ex: URL -> "http://base.com" and params is -> ["a" = "aaa", "b" = 2]
     *
     * * Result in case of get request -> "http://base.com?a=aaa&b=2"
     *
     * * Result in case of post request -> "http://base.com" & body params is -> {"a":"aaa", "b":2}
     *
     * @param params
     */
    data class Standard(var params: NetworkRequestParameters) : NetworkRequestParametersType()

    /**
     * Composite should be used if you have to combine both query and body params within the same request
     *
     * Ex: URL -> "http://base.com" and queryParams is -> ["a" = "aaa", "b" = 2] & bodyParams is-> ["c" = "ccc", "d" = 3]
     *
     * * Result in case of get request -> "http://base.com?a=aaa&b=2" & body is nothing as it's a get request
     *
     * * Result in case of post request -> "http://base.com?a=aaa&b=2" & body params is -> {"c":"ccc", "b":3}
     *
     */
    data class Composite(var bodyParams: NetworkRequestParameters, var queryParams: NetworkRequestParameters) : NetworkRequestParametersType()
}