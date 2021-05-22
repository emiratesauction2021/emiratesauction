package com.ea.emiratesauction.common.di

import com.ea.emiratesauction.core.network.managers.retrofitManager.RetrofitNetworkProvider
import com.ea.emiratesauction.core.network.managers.defaultManager.NetworkManager
import com.ea.emiratesauction.core.network.managers.retrofitManager.RetrofitAPIs
import com.jakewharton.retrofit2.adapter.kotlin.coroutines.CoroutineCallAdapterFactory
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ApplicationComponent
import okhttp3.ConnectionSpec
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.converter.gson.GsonConverterFactory
import java.util.*
import java.util.concurrent.TimeUnit
import javax.inject.Singleton


@Module
@InstallIn(ApplicationComponent::class)
object AppModule {


    @Singleton
    @Provides
    fun provideRetrofitAPIs() = RetrofitAPIs



    @Singleton
    @Provides
    fun provideGsonConverterFactory() =  GsonConverterFactory.create()

    @Singleton
    @Provides
    fun provideCallAdapterFactory() =   CoroutineCallAdapterFactory()


    @Singleton
    @Provides
    fun provideOkHttpClient():OkHttpClient{
        val logging = HttpLoggingInterceptor()
        logging.level = HttpLoggingInterceptor.Level.BODY
        val okkHttpclient = OkHttpClient.Builder()
                .connectTimeout(2, TimeUnit.MINUTES)
                .writeTimeout(2, TimeUnit.MINUTES) // write timeout
                .readTimeout(2, TimeUnit.MINUTES)
                .addInterceptor(logging)
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
        return  okkHttpclient
    }





    @Singleton
    @Provides
    fun provideNetWorkBuilder( okkHttpclient: OkHttpClient,
                               mGCOnvert: GsonConverterFactory,
                               mCallAdapter:CoroutineCallAdapterFactory) =
        RetrofitNetworkProvider(
            okkHttpclient,
            mGCOnvert,
            mCallAdapter
        )

    @Singleton
    @Provides
    fun provideNetworkManager(networkProvider: RetrofitNetworkProvider) =
        NetworkManager(
            networkProvider
        )
}