package com.ea.emiratesauction.features.popularPeoples.domain.usecase

import com.ea.emiratesauction.core.network.result.RequestResult
import com.ea.emiratesauction.features.popularPeoples.requestTarget.PopularPeoplesRequestTarget
import com.ea.emiratesauction.common.base.domain.BaseUseCase
import com.ea.emiratesauction.features.popularPeoples.domain.model.FailData
import com.ea.emiratesauction.features.popularPeoples.domain.model.SuccessData
import com.ea.emiratesauction.features.popularPeoples.domain.repository.PopularPeopleListRepo
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