package com.ea.emiratesauction.common.network_layer.retrofit_dependencies

import com.ea.emiratesauction.common.utils.ApiEndPoints
import com.jakewharton.retrofit2.adapter.kotlin.coroutines.CoroutineCallAdapterFactory
import okhttp3.ConnectionSpec
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.*
import java.util.*
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

        operator fun invoke(): RetrofitAPIs {
            val logging = HttpLoggingInterceptor()
            logging.level = HttpLoggingInterceptor.Level.BODY
            val okkHttpclient = OkHttpClient.Builder()
                    .connectTimeout(2, TimeUnit.MINUTES)
                    .writeTimeout(2, TimeUnit.MINUTES) // write timeout
                    .readTimeout(2, TimeUnit.MINUTES)
                    .addInterceptor(logging)
//                    .addInterceptor(networkConnectionInterceptor)
                    .connectionSpecs(
                            Arrays.asList(
                                    ConnectionSpec.MODERN_TLS,
                                    ConnectionSpec.COMPATIBLE_TLS,
                                    ConnectionSpec.CLEARTEXT
                            )
                    )
                    .followRedirects(true)
                    .followSslRedirects(true)
                    .retryOnConnectionFailure(true)
                    .cache(null)
                    .build()
            /*val headerInterceptor = Interceptor {

            }*/
            return Retrofit.Builder()
                    .client(okkHttpclient)
                    //.baseUrl("https://api.emiratesauction.com/v2/")
                    // .baseUrl(BuildConfig.BASE_URL)
                    // .baseUrl(Consts.TESTING_BASE_URL)
                    .baseUrl(ApiEndPoints.BASE_API_URL)
//                .baseUrl("https://api.eas.ae/v2/")
                    // .baseUrl("http://eaapi2.dev.arabiansystems.com/v2/")

                    .addConverterFactory(GsonConverterFactory.create())
                    .addCallAdapterFactory(CoroutineCallAdapterFactory())
//                    .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                    .build()
                    .create(RetrofitAPIs::class.java)
        }
    }
}