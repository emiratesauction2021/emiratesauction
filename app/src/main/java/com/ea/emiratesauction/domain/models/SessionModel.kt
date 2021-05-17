package com.ea.emiratesauction.domain.models

data class CreateSessionReqBody(
        val request_token:String
)

data class CreateSessionResponse(
        val success: Boolean,
    val status_code: Int,
    val status_message : String?
)
