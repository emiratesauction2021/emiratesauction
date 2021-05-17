package com.ea.emiratesauction.domain.reprositories

import com.ea.emiratesauction.domain.models.CreateSessionReqBody
import com.ea.emiratesauction.domain.models.CreateSessionResponse

interface CreateSessionRepo {

    suspend fun createNewSession(requestBody: CreateSessionReqBody): CreateSessionResponse?
}