package com.ea.emiratesauction.data.datasource.api.model

enum class ResponseErrorCode(val code: Int) {
    // HTTP Generic Errors
    NO_CONNECTION_ERROR(22),
    SERVER_NOT_REACHABLE(500),
    UNAUTHORIZED(401),
    NOT_FOUND(404),

    // Internal Server Errors
    INTERNAL_INTERNET_CONNECTION_LOST(111), // example
    INTERNAL_NO_RESPONSE(222), // example

    SOME_ERROR_HAPPEN(0) // in case some else happen
}