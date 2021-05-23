package com.ea.emiratesauction.core.network.managers.retrofitManager

import retrofit2.Response
import retrofit2.http.*
import java.util.concurrent.TimeUnit


interface RetrofitAPIs {

    @POST
    @JvmSuppressWildcards
    suspend fun  requestPOSTMethod(
            @Url url: String,
            @Body params: Any,
            @HeaderMap headers: Map<String, Any>
    ): Response<Any>

    @GET
    @JvmSuppressWildcards
    suspend fun  requestGETMethod(
            @Url url: String,
            @QueryMap(encoded = true) params: Map<String, Any>,
            @HeaderMap headers: Map<String, Any>
    ): Response<Any>

    @PUT
    suspend fun<T>  requestPUTMethod(
            @Url url: String,
            @Body params: Any,
            @HeaderMap headers: Map<String, Any>
    ): Response<T>

    @DELETE
    suspend fun<T>  requestDELETEMethod(
            @Url url: String,
            @Body params: Any,
            @HeaderMap headers: Map<String, String>
    ):Response<T>



    companion object {

        operator fun invoke(){
        }
    }
}