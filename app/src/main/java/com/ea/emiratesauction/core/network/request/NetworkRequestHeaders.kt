package com.ea.emiratesauction.core.network.request
import com.ea.emiratesauction.core.network.request.DefaultNetworkRequestHeader
/**
 * Please see
 *  @property DefaultNetworkRequestHeader to know what is usage for
 */
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

