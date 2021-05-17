package com.ea.emiratesauction.common.di



import com.ea.emiratesauction.common.network_layer.layer_dependencies.NetworkManagerImp
import com.ea.emiratesauction.data.network.PopularPeopleListRepoImp
import com.ea.emiratesauction.domain.usecases.GetPopularPeopleListUseCase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ActivityComponent

@Module
@InstallIn(ActivityComponent::class)
object ActivityModule {

    @Provides
    fun providePopularPeopleListUseCase(mPopularPeopleListRepo: PopularPeopleListRepoImp) = GetPopularPeopleListUseCase(mPopularPeopleListRepo)



    @Provides
    fun providePopularPeopleListRepo(mNetworkManager: NetworkManagerImp) = PopularPeopleListRepoImp(mNetworkManager)

}