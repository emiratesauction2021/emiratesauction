package com.ea.emiratesauction.core.network.objectMapper

import com.google.gson.Gson

object ObjectMapper {

    /**
     * @param responseObject is api response json
     * @param type is response model Type
     * @param T is response class Type return
     *
     */

    fun <T> mapToObject(responseObject: Any?, type: Class<T>): T {
        val gson = Gson()
        val json = gson.toJson(responseObject)
        return gson.fromJson(json, type)
    }

}