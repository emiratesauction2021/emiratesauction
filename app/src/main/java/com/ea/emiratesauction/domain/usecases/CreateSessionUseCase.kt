package com.ea.emiratesauction.domain.usecases

import com.ea.emiratesauction.domain.models.CreateSessionReqBody
import com.ea.emiratesauction.domain.models.CreateSessionResponse
import com.ea.emiratesauction.domain.reprositories.CreateSessionRepo
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import java.lang.Exception

class CreateSessionUseCase(val createSessionRepo: CreateSessionRepo) : UseCase<CreateSessionReqBody, CreateSessionResponse?>() {
    override  fun execute(params: CreateSessionReqBody): Flow<CreateSessionResponse?> {
      return  flow {
            try {
                emit(createSessionRepo.createNewSession(params))
            } catch (e: Exception) {
                emit(null)
            }

        }


        
    }
}