package com.ea.emiratesauction.core.utilities.network

import com.ea.emiratesauction.core.constants.network.parameters.NetworkRequestParameters
import java.net.URLEncoder

/**
 * this class is used for make Combination of request type Composite
 * @see NetworkRequestParameters
 */

/**
 * The encoding types which are supported by the Network Utility
 */
enum class EncodeType(val type: String){
    UTF_8("UTF-8")
}


class NetworkUtility {
    companion object {
        /**
         * Encoding Parameters to URL
         *
         * @param url The request url
         *
         * @param parameters The query parameters
         *
         * @return The URL after adding the query params to it
         *
         * @throws Exception if the encoding is failed
         *
         */
        fun encodeParametersToURL(url: String, parameters: NetworkRequestParameters) : String {
            val url = if(url.contains("?")) url else "$url?"
            return url + parameters.map {(k, v) -> "${encodeString(k, EncodeType.UTF_8)}=${encodeString(v, EncodeType.UTF_8)}"}.joinToString("&")
        }

        /**
         * Encodes string to a specific type
         *
         * @param obj The object to be encoded
         *
         * @param encodeType The type of the encoding
         *
         * @return The string after the encoding is done
         *
         * @throws Exception if the encoding is failed
         */
        private fun encodeString(obj: Any, encodeType: EncodeType): String {
            if(obj is String) {
                return URLEncoder.encode(obj, encodeType.type)
            }else {
                throw IllegalArgumentException("$obj is not a string - encoding accepts only string values")
            }
        }
    }
}
