package com.ea.emiratesauction.features.test_toBeDeleted.network.domain.usecase

import com.ea.emiratesauction.core.network.result.RequestResult
import com.ea.emiratesauction.features.test_toBeDeleted.network.requestTarget.PopularPeoplesRequestTarget
import com.ea.emiratesauction.core.common.base.domain.BaseUseCase
import com.ea.emiratesauction.features.test_toBeDeleted.network.domain.model.FailData
import com.ea.emiratesauction.features.test_toBeDeleted.network.domain.model.SuccessData
import com.ea.emiratesauction.features.test_toBeDeleted.network.domain.repository.PopularPeopleListRepo
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class GetPopularPeopleListUseCase @Inject constructor(private val popularPeopleListRepo: PopularPeopleListRepo) :
        BaseUseCase<PopularPeoplesRequestTarget, RequestResult<SuccessData, FailData>>() {

    override fun execute(params: PopularPeoplesRequestTarget): Flow<RequestResult<SuccessData, FailData>> {
        return flow {

            emit(popularPeopleListRepo.getPopularPeopleList(params))

        }

    }
}