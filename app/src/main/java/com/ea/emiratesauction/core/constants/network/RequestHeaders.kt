package com.ea.emiratesauction.core.constants.network

/// HTTP Header Fields
enum class HTTPHeaderField(val value: String) {
    AUTHENTICATION("Authorization"),
    CONTENT_TYPE("Content-Type"),
    ACCEPT_TYPE("Accept"),
    ACCEPT_LANGUAGE("Accept-Language"),
    ACCEPT_ENCODING("Accept-Encoding")
}

/// HTTP Custom Header Types
enum class CustomHTTPHeaderField(val value: String) {

}

/// HTTP Header Content Types
enum class ContentType(val value: String) {
    JSON("application/json")
}