package com.ea.emiratesauction.common.network_layer.retrofit_dependencies

import android.util.Log
import com.ea.emiratesauction.common.network_layer.ResponseStatus
import com.ea.emiratesauction.common.network_layer.layer_dependencies.RequestMethod
import com.ea.emiratesauction.common.network_layer.layer_dependencies.RequestProvider
import com.ea.emiratesauction.common.network_layer.layer_dependencies.ResultWrapper
import com.ea.emiratesauction.common.network_layer.layer_dependencies.interfaces.RESTClientInterface
import com.google.gson.Gson
import retrofit2.HttpException
import retrofit2.Response
import java.io.IOException
import java.lang.Exception
import javax.inject.Inject


@Suppress("UNCHECKED_CAST")
class RetrofitBuilder @Inject constructor(val retrofit: RetrofitAPIs) : RESTClientInterface {
    val TAG = "RetrofitBuilder"
    override suspend fun <T> callRequest(provider: RequestProvider<T>): ResultWrapper<T> {

        if (provider.requestType == RequestMethod.GET) {
            val response = retrofit.requestGETMethod(
                    url = provider.EndPointUrl,
                    headers = provider.headersMap,
                    params = provider.requestParams
            )
            Log.d(TAG, response.toString())

            return wrapResponse(response,provider.responseClassType)
        } else if (provider.requestType == RequestMethod.POST) {

            val response = retrofit.requestPOSTMethod(
                    url = provider.EndPointUrl,
                    headers = provider.headersMap,
                    params = provider.requestParams
            )
            return wrapResponse(response,provider.responseClassType)
        } else {
             throw Exception("You need to set NetworkTarget.requestType")
        }
    }

  private  fun<T> wrapResponse(responseData: Response<Any>,responseClass: Class<T>): ResultWrapper<T> {
        try {

            return if (responseData.code().equals(ResponseStatus.SUCCESS.status)) {

                val x = mapToObject<T>(responseData.body()!!,responseClass)

                ResultWrapper.Success(x)
//                var responseData = response.body() as BaseDataWrapper<Any>
//                if (responseData.code!!.equals(ResponseStatus.SUCCESS.status)) {
//                    ResultWrapper.Success(response.body()!!)
//                } else {
//                    if (responseData.code!! != ResponseStatus.AUTHORIZATION.status) {
////                        var errorResponse = ErrorResponse(responseData.message)
//                        ResultWrapper.CustomError(responseData.code, responseData.message)
//                    } else {
//                        ResultWrapper.AuthorizationError
//                    }
//
//                }
            } else if (responseData.code().equals(ResponseStatus.AUTHORIZATION.status)) {
                ResultWrapper.AuthorizationError
            } else {
                ResultWrapper.ServerError
            }
        } catch (throwable: Throwable) {
            return when (throwable) {
                is IOException -> ResultWrapper.NetworkError
                is HttpException -> {
                    val code = throwable.code()
                    if (code != ResponseStatus.AUTHORIZATION.status) {
//                        val errorResponse = convertErrorBody(throwable)
                        ResultWrapper.CustomError(code, throwable.message())
                    } else {
                        ResultWrapper.AuthorizationError
                    }
                }
                else -> {
                    ResultWrapper.CustomError(null, null)
                }
            }
        }

    }

    fun <T> mapToObject(map: Any, type: Class<T>): T {
       // if (map == null) return null
        val gson = Gson()
        val json = gson.toJson(map)
        return gson.fromJson(json, type)
    }
}