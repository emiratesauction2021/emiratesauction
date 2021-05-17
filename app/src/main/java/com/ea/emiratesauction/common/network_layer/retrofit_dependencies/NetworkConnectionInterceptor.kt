package com.ea.emiratesauction.common.network_layer.retrofit_dependencies

import android.content.Context
import android.net.ConnectivityManager
import android.net.NetworkCapabilities
import android.os.Build
import androidx.annotation.RequiresApi
import okhttp3.Interceptor
import okhttp3.Response

class NetworkConnectionInterceptor (context: Context) : Interceptor {

    private val applicationContext = context.applicationContext

    @RequiresApi(Build.VERSION_CODES.M)
    override fun intercept(chain: Interceptor.Chain): Response {
        val request = chain.request().newBuilder()
                .addHeader("Content-Type", "application/json")
                .addHeader("Accept", "application/json")
//            .addHeader(Consts.BUILD_VERSION, "EmiratesAuction/1.6.3")
//            .addHeader(Consts.DEVICE_ID, sharedPreferences.getString(Consts.GCM_TOKEN, ""))
//            .addHeader(Consts.DEVICE_INFO, Util.getDeviceInfo(applicationContext))
//            .addHeader(Consts.DEVICE_LANG, PreferenceProvider(applicationContext).getSavedLanguage().equals(Consts.ARABIC).toString())

                .build()
//        if (!isInternetAvailable()) {
//            throw IOException("Make sure you have an active data connection")
//        } else {
        return chain.proceed(request)
//        }

    }


//
//    @RequiresApi(Build.VERSION_CODES.M)
//    fun isInternetAvailable(): Boolean {
//        var result = false
//        val connectivityManager =
//                applicationContext.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager?
//        connectivityManager?.let {
//            it.getNetworkCapabilities(connectivityManager.activeNetwork)?.apply {
//                result = when {
//                    hasTransport(NetworkCapabilities.TRANSPORT_WIFI) -> true
//                    hasTransport(NetworkCapabilities.TRANSPORT_CELLULAR) -> true
//                    else -> false
//                }
//            }
//        }
//        return result
//    }


}