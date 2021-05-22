package com.ea.emiratesauction.core.network.objectMapper

import com.google.gson.Gson

class ObjectMapper {
    companion object {
        fun <T> mapToObject(map: Any?, type: Class<T>): T {
            val gson = Gson()
            val json = gson.toJson(map)
            return gson.fromJson(json, type)
        }
    }
}