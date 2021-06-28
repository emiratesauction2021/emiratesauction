package com.ea.emiratesauction.core.network.managers.retrofitManager

import retrofit2.Response
import retrofit2.http.*
import java.util.concurrent.TimeUnit

/**
 * Retrofit APIs Handles the different methods supported by the retrofit
 */
interface RetrofitAPIs {
    /**
     * Do a request using retrofit using POST HTTP method
     *
     * @param url The url of the endpoint
     *
     * @param params The parameters associated with the request
     *
     * @param headers The headers associated with the request
     */
    @POST
    @JvmSuppressWildcards
    suspend fun  requestPOSTMethod(
            @Url url: String,
            @Body params: Any,
            @HeaderMap headers: Map<String, Any>
    ): Response<Any>

    /**
     * Do a request using retrofit using GET HTTP method
     *
     * @param url The url of the endpoint
     *
     * @param params The parameters associated with the request
     *
     * @param headers The headers associated with the request
     */
    @GET
    @JvmSuppressWildcards
    suspend fun  requestGETMethod(
            @Url url: String,
            @QueryMap(encoded = true) params: Map<String, Any>,
            @HeaderMap headers: Map<String, Any>
    ): Response<Any>

    /**
     * Do a request using retrofit using PU HTTP method
     *
     * @param url The url of the endpoint
     *
     * @param params The parameters associated with the request
     *
     * @param headers The headers associated with the request
     */
    @PUT
    suspend fun<T>  requestPUTMethod(
            @Url url: String,
            @Body params: Any,
            @HeaderMap headers: Map<String, Any>
    ): Response<T>

    /**
     * Do a request using retrofit using DELETE HTTP method
     *
     * @param url The url of the endpoint
     *
     * @param params The parameters associated with the request
     *
     * @param headers The headers associated with the request
     */
    @DELETE
    suspend fun<T>  requestDELETEMethod(
            @Url url: String,
            @Body params: Any,
            @HeaderMap headers: Map<String, String>
    ):Response<T>



    companion object {
        /**
         * The initial invoke
         */
        operator fun invoke(){

        }
    }
}