package com.ea.emiratesauction.core.network.managers.retrofitManager

import android.util.Log
import com.ea.emiratesauction.core.constants.network.NetworkRequestParameters
import com.ea.emiratesauction.core.constants.network.NetworkRequestParametersType
import com.ea.emiratesauction.core.network.request.BaseNetworkRequest
import com.ea.emiratesauction.core.network.internalError.InternalNetworkError
import com.ea.emiratesauction.core.network.internalError.InternalNetworkErrorInterface
import com.ea.emiratesauction.core.constants.network.RequestHTTPMethodType
import com.ea.emiratesauction.core.constants.network.RequestParameterEncoding
import com.ea.emiratesauction.core.network.result.RequestResult
import com.ea.emiratesauction.core.network.managers.interfaces.NetworkProvider
import com.ea.emiratesauction.core.network.responseHandler.failure.ErrorHandler
import com.ea.emiratesauction.core.network.responseHandler.success.SuccessHandler
import com.ea.emiratesauction.core.utilities.network.NetworkUtility
import com.ea.emiratesauction.core.utilities.network.NetworkValidator
import com.jakewharton.retrofit2.adapter.kotlin.coroutines.CoroutineCallAdapterFactory
import okhttp3.OkHttpClient
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.io.Serializable
import java.lang.Exception
import java.util.concurrent.TimeUnit
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

    override fun httpMethodValidator(parametersType: NetworkRequestParametersType, method: RequestHTTPMethodType, encoding: RequestParameterEncoding) {
        NetworkValidator.httpMethodValidator(parametersType, method, encoding)
    }

    private suspend fun <T : Serializable,E : InternalNetworkErrorInterface> makeRequest(request: BaseNetworkRequest, successModel: Class<T>,
                                                                                         errorModel: Class<E>): RequestResult<T, E> {
        if (currentBaseUrl != request.baseURL.baseUrl || retrofit != null) {
            currentBaseUrl = request.baseURL.baseUrl
            retrofit = Retrofit.Builder()
                .client(okkHttpclient)
                .baseUrl(request.baseURL.baseUrl)
                .addConverterFactory(gConvert)
                .addCallAdapterFactory(callAdapter)
                .build()
                .create(RetrofitAPIs::class.java)

        }

        // Check for any violations in teh request setup
        this.httpMethodValidator(request.parameters, request.httpMethod, request.encoding)

        var endPoint = request.endPoint
        var parameters = when(val paramsType = request.parameters) {
            is NetworkRequestParametersType.Standard -> {
                paramsType.params
            }
            is NetworkRequestParametersType.Composite -> {
                endPoint = NetworkUtility.encodeParametersToURL(endPoint, paramsType.queryParams)
                paramsType.bodyParams
            }
        }

        when (request.httpMethod) {
            RequestHTTPMethodType.GET -> {
                retrofit?.let {
                    val response = it.requestGETMethod(
                            url = endPoint,
                            headers = request.headers,
                            params = parameters
                    )

                    Log.d(TAG, response.toString())

                    return wrapResponse(response, successModel,errorModel)
                }
            }
            RequestHTTPMethodType.POST -> {
                retrofit?.let {
                    val response = it.requestPOSTMethod(
                            url = endPoint,
                            headers = request.headers,
                            params = parameters
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