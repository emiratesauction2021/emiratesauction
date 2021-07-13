package com.ea.emiratesauction.core.common.di



import com.ea.emiratesauction.core.network.managers.networkManager.NetworkManager
import com.ea.emiratesauction.features.test_toBeDeleted.network.data.repository.PopularPeopleListRepoImp
import com.ea.emiratesauction.features.test_toBeDeleted.network.domain.repository.PopularPeopleListRepo
import com.ea.emiratesauction.features.test_toBeDeleted.network.domain.usecase.GetPopularPeopleListUseCase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ActivityComponent
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object ActivityModule {
    @Singleton
    @Provides
    fun providePopularPeopleListRepo(mNetworkProvider: NetworkManager): PopularPeopleListRepo = PopularPeopleListRepoImp(mNetworkProvider)

    @Singleton
    @Provides
    fun providePopularPeopleListUseCase(mPopularPeopleListRepo: PopularPeopleListRepoImp) = GetPopularPeopleListUseCase(mPopularPeopleListRepo)

}