package com.ea.emiratesauction.core.utilities.network
import com.ea.emiratesauction.core.constants.network.NetworkRequestParametersType
import com.ea.emiratesauction.core.constants.network.NetworkRequestParameters
import java.net.URLEncoder

/**
 * this class is used for make Combination of request type Composite
 * @see NetworkRequestParametersType
 */

enum class EncodeType(val type: String){
    UTF_8("UTF-8")
}


class NetworkUtility {
    companion object {
        fun encodeParametersToURL(url: String, parameters: NetworkRequestParameters) : String {
            val url = if(url.contains("?")) url else "$url?"
            return url + parameters.map {(k, v) -> "${encodeString(k, EncodeType.UTF_8)}=${encodeString(v, EncodeType.UTF_8)}"}.joinToString("&")
        }

        private fun encodeString(obj: Any, encodeType: EncodeType): String {
            if(obj is String) {
                return URLEncoder.encode(obj, encodeType.type)
            }else {
                throw IllegalArgumentException("$obj is not a string - encoding accepts only string values")
            }
        }
    }
}
