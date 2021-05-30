package com.ea.emiratesauction.core.network.responseHandler.failure

import com.ea.emiratesauction.core.network.internalError.InternalNetworkErrorInterface
import com.ea.emiratesauction.core.network.internalError.NetworkError
import com.ea.emiratesauction.core.network.objectMapper.ObjectMapper
import com.ea.emiratesauction.core.network.result.RequestResult
import com.ea.emiratesauction.core.constants.network.NetworkErrors
import java.io.Serializable

/**
 * this handler is to map data with return model
 * here we know that error is fail then we will handle two cases
 * @property handleHTTPErrors for external errors
 * @property checkForInternalError for errors return from server response
 */

class ErrorHandler {
    companion object {
        fun <T : Serializable,E : InternalNetworkErrorInterface> handleHTTPErrors(code: Int, message: String?): RequestResult<T, E> {
            // common External Errors
            val errorCode = when (code) {

                400 -> NetworkErrors.BAD_REQUEST

                401 -> NetworkErrors.UNAUTHORIZED

                403 -> NetworkErrors.FORBIDDEN

                404 -> NetworkErrors.NOT_FOUND

                500 -> NetworkErrors.SERVER_NOT_REACHABLE

                else -> NetworkErrors.GENERIC_HTTP_ERROR
            }

            return RequestResult.Fail(NetworkError(errorCode, null))
        }

        fun <E : InternalNetworkErrorInterface>checkForInternalError(responseData: Any, errorModel: Class<E>): E? {
            val failedMappedData = ObjectMapper.mapToObject(responseData, errorModel)

            if (failedMappedData.status != null && failedMappedData.status == false) {
                return failedMappedData
            }

            return null
        }
    }
}
