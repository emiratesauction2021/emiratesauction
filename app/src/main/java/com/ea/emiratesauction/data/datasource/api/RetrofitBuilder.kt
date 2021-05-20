package com.ea.emiratesauction.data.datasource.api

import android.util.Log
import com.ea.emiratesauction.common.base.domain.RequestTarget
import com.ea.emiratesauction.data.datasource.api.model.ApiErrorModel
import com.ea.emiratesauction.data.datasource.api.model.BaseDataModel
import com.ea.emiratesauction.data.datasource.api.model.ResponseErrorCode
import com.ea.emiratesauction.network_layer.model.RequestMethod
import com.ea.emiratesauction.network_layer.ResultWrapper
import com.ea.emiratesauction.network_layer.interfaces.NetworkManager
import com.google.gson.Gson
import com.jakewharton.retrofit2.adapter.kotlin.coroutines.CoroutineCallAdapterFactory
import okhttp3.OkHttpClient
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.lang.Exception
import javax.inject.Inject


@Suppress("UNCHECKED_CAST")
class RetrofitBuilder @Inject constructor(
        val okkHttpclient: OkHttpClient,
        val mGCOnvert: GsonConverterFactory,
        val mCallAdapter: CoroutineCallAdapterFactory
) : NetworkManager {

    private var currentBaseUrl = ""
    private var retrofit: RetrofitAPIs? = null

    val TAG = "RetrofitBuilder"
    override suspend fun <T> callRequest(mTarget: RequestTarget<T>): ResultWrapper<T> {

        if (!currentBaseUrl.equals(mTarget.baseUrl.baseUrl) || retrofit != null) {
            currentBaseUrl = mTarget.baseUrl.baseUrl
            retrofit = Retrofit.Builder()
                    .client(okkHttpclient)
                    .baseUrl(mTarget.baseUrl.baseUrl)
                    .addConverterFactory(mGCOnvert)
                    .addCallAdapterFactory(mCallAdapter)
                    .build()
                    .create(RetrofitAPIs::class.java)

        }


        if (mTarget.requestType == RequestMethod.GET) {
            val response = retrofit!!.requestGETMethod(
                    url = mTarget.endPointUrl,
                    headers = mTarget.headersMap,
                    hParams = mTarget.requestQueryParams
            )
            Log.d(TAG, response.toString())



            return wrapResponse(response, mTarget.responseClassType)
        } else if (mTarget.requestType == RequestMethod.POST) {

            val response = retrofit!!.requestPOSTMethod(
                    url = mTarget.endPointUrl,
                    headers = mTarget.headersMap,
                    bParams = mTarget.requestBodyParams,
                    hParams = mTarget.requestQueryParams
            )
            return wrapResponse(response, mTarget.responseClassType)
        } else {
            throw Exception("You need to set NetworkTarget.requestType")
        }
    }

    private fun <T> wrapResponse(responseData: Response<BaseDataModel<Any>>, responseClass: Class<T>): ResultWrapper<T> {

            return if (responseData.isSuccessful) {

                val mData = responseData.body()!!

                if (mData.status) {
                    val x = mapToObject(mData.data, responseClass)
                    ResultWrapper.Success(x)

                } else {
                    val errorCode = when (responseData.body()!!.code) {
                        ResponseErrorCode.INTERNAL_INTERNET_CONNECTION_LOST.code -> ResponseErrorCode.INTERNAL_INTERNET_CONNECTION_LOST
                        ResponseErrorCode.INTERNAL_NO_RESPONSE.code -> ResponseErrorCode.INTERNAL_NO_RESPONSE
                        else -> ResponseErrorCode.SOME_ERROR_HAPPEN
                    }
                    ResultWrapper.Fail(ApiErrorModel(errorCode, mData.message, mData.data))
                }


            } else {
                HandleHTTPErrors(responseData.code(), responseData.message())
            }
    }

    private fun <T> HandleHTTPErrors(code: Int, message: String?): ResultWrapper<T> {
        val errorCode = when (code) {

            ResponseErrorCode.NOT_FOUND.code -> ResponseErrorCode.NOT_FOUND

            ResponseErrorCode.NO_CONNECTION_ERROR.code -> ResponseErrorCode.NO_CONNECTION_ERROR

            ResponseErrorCode.SERVER_NOT_REACHABLE.code -> ResponseErrorCode.SERVER_NOT_REACHABLE

            ResponseErrorCode.UNAUTHORIZED.code -> ResponseErrorCode.UNAUTHORIZED

            else -> ResponseErrorCode.SOME_ERROR_HAPPEN
        }
        return ResultWrapper.Fail(ApiErrorModel(errorCode, message, null))
    }


    fun <T> mapToObject(map: Any?, type: Class<T>): T? {
        if (map == null) return null
        val gson = Gson()
        val json = gson.toJson(map)
        return gson.fromJson(json, type)
    }
}