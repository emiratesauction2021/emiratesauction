package com.ea.emiratesauction.data.datasource.api

import android.util.Log
import com.ea.emiratesauction.common.base.domain.RequestTarget
import com.ea.emiratesauction.common.utils.ApiEndPoints
import com.ea.emiratesauction.network_layer.model.ResponseStatus
import com.ea.emiratesauction.network_layer.model.RequestMethod
import com.ea.emiratesauction.network_layer.ResultWrapper
import com.ea.emiratesauction.network_layer.interfaces.NetworkManager
import com.google.gson.Gson
import com.jakewharton.retrofit2.adapter.kotlin.coroutines.CoroutineCallAdapterFactory
import okhttp3.OkHttpClient
import retrofit2.HttpException
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.io.IOException
import java.lang.Exception
import javax.inject.Inject


@Suppress("UNCHECKED_CAST")
class RetrofitBuilder @Inject constructor(
        val okkHttpclient: OkHttpClient,
        val mGCOnvert: GsonConverterFactory,
        val mCallAdapter: CoroutineCallAdapterFactory
) : NetworkManager {
    val TAG = "RetrofitBuilder"
    override suspend fun <T> callRequest(mTarget: RequestTarget<T>): ResultWrapper<T> {

        val retrofit = Retrofit.Builder()
                .client(okkHttpclient)
                .baseUrl(mTarget.baseUrl.baseUrl)
                .addConverterFactory(mGCOnvert)
                .addCallAdapterFactory(mCallAdapter)
                .build()
                .create(RetrofitAPIs::class.java)


        if (mTarget.requestType == RequestMethod.GET) {
            val response = retrofit.requestGETMethod(
                    url = mTarget.endPointUrl,
                    headers = mTarget.headersMap,
                    params = mTarget.requestParams
            )
            Log.d(TAG, response.toString())

            return wrapResponse(response, mTarget.responseClassType)
        } else if (mTarget.requestType == RequestMethod.POST) {

            val response = retrofit.requestPOSTMethod(
                    url = mTarget.endPointUrl,
                    headers = mTarget.headersMap,
                    params = mTarget.requestParams
            )
            return wrapResponse(response, mTarget.responseClassType)
        } else {
            throw Exception("You need to set NetworkTarget.requestType")
        }
    }

    private fun <T> wrapResponse(responseData: Response<Any>, responseClass: Class<T>): ResultWrapper<T> {
        try {

            return if (responseData.code().equals(ResponseStatus.SUCCESS.status)) {

                val x = mapToObject<T>(responseData.body()!!, responseClass)

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