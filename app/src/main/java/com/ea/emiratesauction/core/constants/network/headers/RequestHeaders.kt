package com.ea.emiratesauction.core.constants.network

/**
 * The Identifiers of the know headers which are being used across different systems
 *
 * @param value The string value of the HTTP header field
 */
enum class HTTPHeaderField(val value: String) {
    AUTHENTICATION("Authorization"),
    CONTENT_TYPE("Content-Type"),
    ACCEPT_TYPE("Accept"),
    ACCEPT_LANGUAGE("Accept-Language"),
    ACCEPT_ENCODING("Accept-Encoding")
}

/**
 * The Identifiers of the custom headers defined specifically in the app
 *
 * @param value The string value of the HTTP header field
 */
enum class CustomHTTPHeaderField(val value: String) {

}

/**
 * The different values which Content Type accepts
 *
 * @param value The string value of the content type
 */
enum class ContentType(val value: String) {
    JSON("application/json")
}