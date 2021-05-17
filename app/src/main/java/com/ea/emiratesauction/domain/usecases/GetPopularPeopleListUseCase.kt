package com.ea.emiratesauction.domain.usecases

import com.ea.emiratesauction.common.network_layer.layer_dependencies.ResultWrapper
import com.ea.emiratesauction.domain.models.PopularPeopleListReqModel
import com.ea.emiratesauction.domain.models.PopularPeopleListResponse
import com.ea.emiratesauction.domain.reprositories.PopularPeopleListRepo
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import java.lang.Exception
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class GetPopularPeopleListUseCase @Inject constructor(private val popularPeopleListRepo: PopularPeopleListRepo) : UseCase<PopularPeopleListReqModel, ResultWrapper<PopularPeopleListResponse>>() {
    override  fun execute(params: PopularPeopleListReqModel): Flow<ResultWrapper<PopularPeopleListResponse>> {
      return  flow {

          emit(popularPeopleListRepo.getPopularPeopleList(params.page))

        }

    }
}