package com.ea.emiratesauction.core.network.objectMapper

import com.google.gson.Gson

object ObjectMapper {
    /**
     * Maps raw data to a specific object model
     *
     * @param responseObject The raw data in a JSON format
     *
     * @param type The object class which will be used to map the raw response - it will be based on the T generic object
     *
     * @return An object of type T after mapping the raw data to the same type T
     */
    fun <T> mapToObject(responseObject: Any?, type: Class<T>): T {
        val gson = Gson()
        val json = gson.toJson(responseObject)
        return gson.fromJson(json, type)
    }

}