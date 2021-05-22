package com.ea.emiratesauction.core.network.networkErrors

enum class NetworkErrors {
    // HTTP Errors
    FORBIDDEN,
    UNAUTHORIZED,
    BAD_REQUEST,
    GENERIC_HTTP_ERROR,
    SERVER_NOT_REACHABLE,
    NOT_FOUND,

    // Internal Errors
    NO_ERROR,
    NO_RESPONSE,
    PARSING_ERROR,
    UNKNOWN_ERROR,
    NO_INTERNET_CONNECTION,
    CONNECTION_LOST,
    REQUEST_TIMEOUT,
    INTERNAL_REQUEST_ERROR
}
