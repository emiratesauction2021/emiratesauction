package com.ea.emiratesauction.common.di

import com.ea.emiratesauction.common.network_layer.retrofit_dependencies.RetrofitBuilder
import com.ea.emiratesauction.common.network_layer.layer_dependencies.NetworkManagerImp
import com.ea.emiratesauction.common.network_layer.retrofit_dependencies.RetrofitAPIs
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ApplicationComponent
import javax.inject.Singleton


@Module
@InstallIn(ApplicationComponent::class)
object AppModule {


    @Singleton
    @Provides
    fun provideRetrofitAPIs() = RetrofitAPIs()

    @Singleton
    @Provides
    fun provideNetWorkBuilder(apis:RetrofitAPIs) = RetrofitBuilder(apis)

    @Singleton
    @Provides
    fun provideNetworkManager(retrofitBuilder: RetrofitBuilder) = NetworkManagerImp(retrofitBuilder)

}