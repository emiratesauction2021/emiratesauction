package com.ea.emiratesauction.data.datasource.api

import android.util.Log
import com.ea.emiratesauction.common.base.domain.BaseNetworkRequest
import com.ea.emiratesauction.data.datasource.api.model.InternalNetworkErrorInterface
import com.ea.emiratesauction.data.datasource.api.model.ResponseErrorCode
import com.ea.emiratesauction.network_layer.model.RequestMethod
import com.ea.emiratesauction.network_layer.ResultWrapper
import com.ea.emiratesauction.core.network.interfaces.NetworkProvider
import com.google.gson.Gson
import com.jakewharton.retrofit2.adapter.kotlin.coroutines.CoroutineCallAdapterFactory
import okhttp3.OkHttpClient
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.io.Serializable
import java.lang.Exception
import javax.inject.Inject


@Suppress("UNCHECKED_CAST")
class RetrofitBuilder @Inject constructor(
        val okkHttpclient: OkHttpClient,
        val mGCOnvert: GsonConverterFactory,
        val mCallAdapter: CoroutineCallAdapterFactory
) : NetworkProvider {

    private var currentBaseUrl = ""
    private var retrofit: RetrofitAPIs? = null

    val TAG = "RetrofitBuilder"

    private fun <T : Serializable,E : InternalNetworkErrorInterface> wrapResponse(responseData: Response<Any>, successTypeClass: Class<T>, failTypeClass: Class<E>):
            ResultWrapper<T,E> {

        return if (responseData.isSuccessful) {

            val mData = responseData.body()!!
            val FailMappedData = mapToObject(mData, failTypeClass)


            if (FailMappedData.status==null||FailMappedData.status==true) {
                val SuccessmappedData = mapToObject(mData, successTypeClass)

                ResultWrapper.Success(SuccessmappedData)

            }
            else {
                ResultWrapper.Fail(FailMappedData, ResponseErrorCode.INTERNAL_SERVER_ERROR)
            }


        } else {
            HandleHTTPErrors<T,E>(responseData.code(), responseData.message())
        }
    }

    private fun <T : Serializable,E : InternalNetworkErrorInterface> HandleHTTPErrors(code: Int, message: String?): ResultWrapper<T,E> {
        val errorCode = when (code) {

            ResponseErrorCode.NOT_FOUND.code -> ResponseErrorCode.NOT_FOUND

            ResponseErrorCode.NO_CONNECTION_ERROR.code -> ResponseErrorCode.NO_CONNECTION_ERROR

            ResponseErrorCode.SERVER_NOT_REACHABLE.code -> ResponseErrorCode.SERVER_NOT_REACHABLE

            ResponseErrorCode.UNAUTHORIZED.code -> ResponseErrorCode.UNAUTHORIZED

            else -> ResponseErrorCode.SOME_ERROR_HAPPEN
        }
        return ResultWrapper.Fail(null, errorCode)
    }


    fun <T> mapToObject(map: Any?, type: Class<T>): T {
//        if (map == null) return null
        val gson = Gson()
        val json = gson.toJson(map)
        return gson.fromJson(json, type)
    }



    override suspend fun <T : Serializable,E : InternalNetworkErrorInterface> callRequest(mTarget: BaseNetworkRequest, successModel: Class<T>,
                                                                                          errorModel: Class<E>): ResultWrapper<T,E> {

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
                    params = mTarget.requestQueryParams
            )
            Log.d(TAG, response.toString())



            return wrapResponse<T,E>(response, successModel,errorModel)
        } else if (mTarget.requestType == RequestMethod.POST) {

            val response = retrofit!!.requestPOSTMethod(
                    url = mTarget.endPointUrl,
                    headers = mTarget.headersMap,
                    params = mTarget.requestBodyParams
//                    hParams = mTarget.requestQueryParams
            )
            Log.d(TAG, response.toString())

            return wrapResponse(response, successModel,errorModel)
        } else {
            throw Exception("You need to set NetworkTarget.requestType")
        }
    }
}