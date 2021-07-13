package com.ea.emiratesauction.core.common.di

import android.content.Context
import android.content.SharedPreferences
import com.ea.emiratesauction.common.utils.BusinessConstants.Companion.EA_SHARED_PREFERENCE
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
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import okhttp3.ConnectionSpec
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton


@Module
@InstallIn(SingletonComponent::class)
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


    @Provides
    fun sharedPreference(@ApplicationContext context: Context): SharedPreferences =
        context.getSharedPreferences(EA_SHARED_PREFERENCE, Context.MODE_PRIVATE)

    @Singleton
    @Provides
    fun provideSharedPreferencesProvider(shared:SharedPreferences): DataPersistenceProvider {
        return SharedPreferencesProvider(shared)
    }

    @Singleton
    @Provides
    fun provideSecuredSharedPreferencesProvider(@ApplicationContext context: Context): DataPersistenceProvider {
        return SecureSharedPreferencesProvider(context)
    }
}