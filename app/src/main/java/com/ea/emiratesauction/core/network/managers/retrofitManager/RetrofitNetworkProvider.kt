package com.ea.emiratesauction.core.network.managers.retrofitManager

import android.util.Log
import com.ea.emiratesauction.common.base.domain.BaseNetworkRequest
import com.ea.emiratesauction.core.network.internalError.InternalNetworkError
import com.ea.emiratesauction.core.network.internalError.InternalNetworkErrorInterface
import com.ea.emiratesauction.network_layer.model.RequestMethod
import com.ea.emiratesauction.core.network.result.RequestResult
import com.ea.emiratesauction.core.network.managers.interfaces.NetworkProvider
import com.ea.emiratesauction.core.network.responseHandler.failure.ErrorHandler
import com.ea.emiratesauction.core.network.responseHandler.success.SuccessHandler
import com.jakewharton.retrofit2.adapter.kotlin.coroutines.CoroutineCallAdapterFactory
import okhttp3.OkHttpClient
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.io.Serializable
import java.lang.Exception
import javax.inject.Inject


@Suppress("UNCHECKED_CAST")
class RetrofitNetworkProvider @Inject constructor(
    private val okkHttpclient: OkHttpClient,
    private val gConvert: GsonConverterFactory,
    private val callAdapter: CoroutineCallAdapterFactory
) : NetworkProvider {

    private var currentBaseUrl = ""
    private var retrofit: RetrofitAPIs? = null

    val TAG = "RetrofitBuilder"

    override suspend fun <T : Serializable,E : InternalNetworkErrorInterface> request(request: BaseNetworkRequest, successModel: Class<T>,
                                                                                      errorModel: Class<E>): RequestResult<T, E> {
        return this.makeRequest(request, successModel, errorModel)
    }

    override suspend fun <T : Serializable> request(
        request: BaseNetworkRequest,
        successModel: Class<T>
    ): RequestResult<T, InternalNetworkErrorInterface> {
        return this.makeRequest(request, successModel, InternalNetworkError::class.java)
    }

    private suspend fun <T : Serializable,E : InternalNetworkErrorInterface> makeRequest(request: BaseNetworkRequest, successModel: Class<T>,
                                                                                 errorModel: Class<E>): RequestResult<T, E> {
        if (currentBaseUrl != request.baseUrl.baseUrl || retrofit != null) {
            currentBaseUrl = request.baseUrl.baseUrl
            retrofit = Retrofit.Builder()
                .client(okkHttpclient)
                .baseUrl(request.baseUrl.baseUrl)
                .addConverterFactory(gConvert)
                .addCallAdapterFactory(callAdapter)
                .build()
                .create(RetrofitAPIs::class.java)

        }


        when (request.requestType) {
            RequestMethod.GET -> {
                retrofit?.let {
                    val response = it.requestGETMethod(
                            url = request.endPointUrl,
                            headers = request.headersMap,
                            params = request.requestQueryParams
                    )

                    Log.d(TAG, response.toString())

                    return wrapResponse(response, successModel,errorModel)
                }
            }
            RequestMethod.POST -> {
                retrofit?.let {
                    val response = it.requestPOSTMethod(
                            url = request.endPointUrl,
                            headers = request.headersMap,
                            params = request.requestBodyParams
                    )
                    Log.d(TAG, response.toString())

                    return wrapResponse(response, successModel,errorModel)
                }
            }
            else -> {
                throw Exception("HTTP Method is currently not supported")
            }
        }

        throw Exception("Retrofit is not initialized")
    }

    private fun <T : Serializable,E : InternalNetworkErrorInterface> wrapResponse(responseData: Response<Any>, successModel: Class<T>, errorModel: Class<E>):
            RequestResult<T, E> {

        return if(responseData.isSuccessful) {
            SuccessHandler.mapSuccessfulResponse(responseData.body(), successModel, errorModel)
        } else {
            ErrorHandler.handleHTTPErrors(responseData.code(), responseData.message())
        }
    }
}