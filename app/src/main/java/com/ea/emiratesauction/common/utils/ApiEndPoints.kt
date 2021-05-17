package com.ea.emiratesauction.common.utils

class ApiEndPoints {
    companion object  {
        val headersMap = hashMapOf<String, Any>("Accept-Language" to "en","accept" to "application/json", "Content-Type" to "application/json-patch+json")

        const val BASE_API_URL = "https://api.themoviedb.org/3/"
        const val BASE_IMAGE_URL = "https://image.tmdb.org/t/p/"
        const val API_KEY = "1cb2fc2b567d64f1a4a5c1e35bf70d30"
        const val POPULAR_PEOPLE_ENDPOINT_URL = "person/popular"
        const val CREATE_SESSION_ENDPOINT_URL ="authentication/session/new"

    }
}