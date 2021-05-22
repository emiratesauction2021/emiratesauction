package com.ea.emiratesauction.common.di



import com.ea.emiratesauction.core.network.managers.defaultManager.NetworkManager
import com.ea.emiratesauction.data.repository.PopularPeopleListRepoImp
import com.ea.emiratesauction.features.popularPeoples.domain.usecase.GetPopularPeopleListUseCase
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
    fun providePopularPeopleListRepo(mNetworkManager: NetworkManager) = PopularPeopleListRepoImp(mNetworkManager)

}