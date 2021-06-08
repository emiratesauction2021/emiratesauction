package com.ea.emiratesauction.core.network.managers.retrofitManager

import android.util.Log
import com.ea.emiratesauction.core.constants.network.NetworkRequestParametersType
import com.ea.emiratesauction.core.network.request.networkRequest.BaseNetworkRequest
import com.ea.emiratesauction.core.network.internalError.errors.InternalNetworkError
import com.ea.emiratesauction.core.network.internalError.interfaces.InternalNetworkErrorInterface
import com.ea.emiratesauction.core.constants.network.httpMethods.RequestHTTPMethodType
import com.ea.emiratesauction.core.network.managers.interfaces.providers.NetworkProviderInterface
import com.ea.emiratesauction.core.network.result.RequestResult
import com.ea.emiratesauction.core.network.responseHandler.failure.ErrorHandler
import com.ea.emiratesauction.core.network.responseHandler.success.SuccessHandler
import com.ea.emiratesauction.core.utilities.network.NetworkUtility
import com.jakewharton.retrofit2.adapter.kotlin.coroutines.CoroutineCallAdapterFactory
import okhttp3.OkHttpClient
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.io.Serializable
import java.lang.Exception
import java.util.concurrent.TimeUnit
import javax.inject.Inject

/**
 * The Retrofit Provider which implements the NetworkProviderInterface to be used by any network manager within the app - is a layer between the
 * Retrofit library and the network manager which wraps the coupled implementation between the app and the Retrofit
 *
 * It provides the basic request functionalities
 *
 * @param builderOkHttpclient The OKHttpClient Builder which is used to build the client used by Retrofit
 *
 * @param gConvert The GSON converter used to parse the data used by Retrofit
 *
 * @param callAdapter The call adapter used by Retrofit to pass the data
 */
@Suppress("UNCHECKED_CAST")
class RetrofitNetworkProvider @Inject constructor(
        private val builderOkHttpclient: OkHttpClient.Builder,
        private val gConvert: GsonConverterFactory,
        private val callAdapter: CoroutineCallAdapterFactory
) : NetworkProviderInterface {

    //******************************************************
    //-------------- Used to preserve the same base url and timeout within the Retrofit ---------
    private var currentBaseUrl = ""
    private var currentTimeOut: Long = 0
    // ------------------------------------------------------------------------------------------
    // ------------- The Retrofit Clients & Interfaces ------------------------------------------
    private lateinit var okkHttpclient: OkHttpClient
    private var retrofit: RetrofitAPIs? = null
    // ------------------------------------------------------------------------------------------
    //******************************************************


    private val tag = "RetrofitBuilder"

    override suspend fun <T : Serializable, E : InternalNetworkErrorInterface> request(
            request: BaseNetworkRequest, successModel: Class<T>,
            errorModel: Class<E>
    ): RequestResult<T, E> {
        return this.makeRequest(request, successModel, errorModel)
    }

    override suspend fun <T : Serializable> request(
            request: BaseNetworkRequest,
            successModel: Class<T>
    ): RequestResult<T, InternalNetworkErrorInterface> {
        return this.makeRequest(request, successModel, InternalNetworkError::class.java)
    }

    /**
     * Make a request and passes the success model and the error model
     *
     * @param request The network request and all of its parameters
     *
     * @param successModel The success class which will be used to map the successful response - it will be based on the T generic object
     * which has to implement Serializable
     *
     * @param errorModel The error class which will be used to map the failed response - it will be based on the E generic object
     * which has to implement InternalNetworkErrorInterface
     *
     * @return A RequestResult class with a success model or error model based on the response from the API
     */
    private suspend fun <T : Serializable, E : InternalNetworkErrorInterface> makeRequest(
            request: BaseNetworkRequest, successModel: Class<T>,
            errorModel: Class<E>
    ): RequestResult<T, E> {

        //******************************************************
        //-------------- Update the OKHTTPClient if the timeout of the request which will be executed isn't the same as the last one used ---------
        if (request.timeOutSeconds != currentTimeOut) {
            currentTimeOut = request.timeOutSeconds
            okkHttpclient = builderOkHttpclient.connectTimeout(request.timeOutSeconds, TimeUnit.SECONDS)
                    .writeTimeout(request.timeOutSeconds, TimeUnit.SECONDS) // write timeout
                    .readTimeout(request.timeOutSeconds, TimeUnit.SECONDS)
                    .build()
        }
        //-------------- Update the RetrofitClient if the base url of the request which will be executed isn't the same as the last one used ---------
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
        //******************************************************

        //******************************************************
        //-------------- Constructing the request and its parameters ---------
        var endPoint = request.endPoint
        var parameters = when (val paramsType = request.parameters) {
            is NetworkRequestParametersType.Standard -> {
                paramsType.params
            }
            is NetworkRequestParametersType.Composite -> {
                endPoint = NetworkUtility.encodeParametersToURL(endPoint, paramsType.queryParams)
                paramsType.bodyParams
            }
        }
        //******************************************************

        //-------------- Call the Retrofit APIs based on the HTTP method used ---------
        when (request.httpMethod) {
            RequestHTTPMethodType.GET -> {
                retrofit?.let {
                    val response = it.requestGETMethod(
                        url = endPoint,
                        headers = request.headers.getAllHeaders(),
                        params = parameters
                    )

                    Log.d(tag, response.toString())

                    return wrapResponse(response, successModel, errorModel)
                }
            }
            RequestHTTPMethodType.POST -> {
                retrofit?.let {
                    val response = it.requestPOSTMethod(
                        url = endPoint,
                        headers = request.headers.getAllHeaders(),
                        params = parameters
                    )
                    Log.d(tag, response.toString())

                    return wrapResponse(response, successModel, errorModel)
                }
            }
            else -> {
                // Throw an exception if the method isn't yet supported
                throw Exception("HTTP Method is currently not supported")
            }
        }

        // Throw an exception if the RetrofitClient isn't initialized yet
        throw Exception("Retrofit is not initialized")
    }

    /**
     * Wraps the response received from RetrofitClient to the RequestResult
     *
     * @param responseData The raw data received from the RetrofitClient
     *
     * @param successModel The success class which will be used to map the successful response - it will be based on the T generic object
     * which has to implement Serializable
     *
     * @param errorModel The error class which will be used to map the failed response - it will be based on the E generic object
     * which has to implement InternalNetworkErrorInterface
     *
     * @return A RequestResult class with a success model or error model based on the response from the API
     */
    private fun <T : Serializable, E : InternalNetworkErrorInterface> wrapResponse(
        responseData: Response<Any>,
        successModel: Class<T>,
        errorModel: Class<E>
    ):
            RequestResult<T, E> {

        // Check the response status and call the success handler or error handler respectively
        return if (responseData.isSuccessful) {
            SuccessHandler.mapSuccessfulResponse(responseData.body(), successModel, errorModel)
        } else {
            ErrorHandler.handleHTTPErrors(responseData.code(), responseData.message())
        }
    }
}