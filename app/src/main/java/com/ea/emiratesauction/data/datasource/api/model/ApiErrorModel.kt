package com.ea.emiratesauction.data.datasource.api.model

data class ApiErrorModel(val errorCode: ResponseErrorCode,
                         val message: String?,
                         val data: Any?)