package com.ea.emiratesauction.core.common.di

import com.ea.emiratesauction.core.deviceData.providers.DataPersistenceProvider
import com.ea.emiratesauction.core.deviceData.providers.SecureSharedPreferencesProvider
import com.ea.emiratesauction.core.deviceData.providers.SharedPreferencesProvider
import com.ea.emiratesauction.core.network.managers.networkManager.NetworkManager
import com.ea.emiratesauction.core.network.managers.retrofitManager.RetrofitNetworkProvider
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
import javax.inject.Singleton


@Module
@InstallIn(ApplicationComponent::class)
object AppModule {


    @Singleton
    @Provides
    fun provideRetrofitAPIs() = RetrofitAPIs


    @Singleton
    @Provides
    fun provideGsonConverterFactory() = GsonConverterFactory.create()

    @Singleton
    @Provides
    fun provideCallAdapterFactory() = CoroutineCallAdapterFactory()


    @Singleton
    @Provides
    fun provideOkHttpClient(): OkHttpClient.Builder {
        val logging = HttpLoggingInterceptor()
        logging.level = HttpLoggingInterceptor.Level.BODY
        return OkHttpClient.Builder()
            .addInterceptor(logging)
            .connectionSpecs(
                listOf(
                    ConnectionSpec.MODERN_TLS,
                    ConnectionSpec.COMPATIBLE_TLS,
                    ConnectionSpec.CLEARTEXT
                )
            )
            .followRedirects(true)
            .followSslRedirects(true)
            .retryOnConnectionFailure(true)
            .cache(null)
    }


    @Singleton
    @Provides
    fun provideNetworkManager(networkProvider: RetrofitNetworkProvider) =
        NetworkManager(networkProvider)

    @Singleton
    @Provides
    fun provideRetrofitBuilder(
        okkHttpclient: OkHttpClient.Builder,
        mGConvert: GsonConverterFactory,
        mCallAdapter: CoroutineCallAdapterFactory
    ) =
        RetrofitNetworkProvider(
            okkHttpclient,
            mGConvert,
            mCallAdapter
        )


    @Singleton
    @Provides
    fun provideSharedPreferencesProvider(): DataPersistenceProvider {
        return SharedPreferencesProvider()
    }

    @Singleton
    @Provides
    fun provideSecuredSharedPreferencesProvider(): DataPersistenceProvider {
        return SecureSharedPreferencesProvider()
    }
}