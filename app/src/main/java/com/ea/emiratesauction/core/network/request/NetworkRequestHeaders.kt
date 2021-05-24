package com.ea.emiratesauction.core.network.request

interface AccessTokenAuthorization{
    val auth : ArrayList<Pair<String, Any>>

}

interface NetworkRequestHeaders:AccessTokenAuthorization{
    val headers:ArrayList<Pair<String, Any>>

    fun getAllHeaders():Map<String, Any>{
        val all = HashMap<String, Any>()
        all.putAll(headers)
        all.putAll(auth)
        return all
    }
}

class DefaultNetworkRequestHeader:NetworkRequestHeaders{
    override val headers: ArrayList<Pair<String, Any>>
        get() = arrayListOf("Accept-Language" to "en","accept" to "application/json", "Content-Type" to "application/json-patch+json")
    override val auth: ArrayList<Pair<String, Any>>
        get() = arrayListOf("user-token" to "asdasdwd-3do2q30d2k-r32-")

}

