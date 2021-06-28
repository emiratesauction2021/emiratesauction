package com.ea.emiratesauction.core.network.responseHandler.failure

import com.ea.emiratesauction.core.network.internalError.interfaces.InternalNetworkErrorInterface
import com.ea.emiratesauction.core.network.internalError.errors.NetworkError
import com.ea.emiratesauction.core.network.objectMapper.ObjectMapper
import com.ea.emiratesauction.core.network.result.RequestResult
import com.ea.emiratesauction.core.constants.network.errors.NetworkErrors
import java.io.Serializable

/**
 * Handles all the errors across the app, whether it's internal or HTTP errors
 *
 * The implementation of the Error handling is decoupled from the providers in order to unify the implementation regardless of the used provider
 */
class ErrorHandler {
    companion object {
        /**
         * Handles the HTTP errors of a response
         *
         * @param code The HTTP error code of the response
         *
         * @param message The message of the error from the response
         *
         * @return RequestResult with the associated HTTP error from the NetworkErrors enum
         */
        fun <T : Serializable,E : InternalNetworkErrorInterface> handleHTTPErrors(code: Int, message: String?): RequestResult<T, E> {
            // Common HTTP Errors
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

        /**
         * Handles the internal errors which occur in the response objects
         *
         * Note: In Response Errors Or Internal Errors -> Means the HTTP Code is 200 but you still have an Error
         *
         * Example: Registration API gives 200 but the email is already existed before
         *
         * @param responseData The raw data of the response
         *
         * @param errorModel The error class which will be used to map the failed response - it will be based on the E generic object
         * which has to implement InternalNetworkErrorInterface
         *
         * @return The internal Error mapped to the E generic object type or Null in case of no errors
         */
        fun <E : InternalNetworkErrorInterface>checkForInternalError(responseData: Any, errorModel: Class<E>): E? {
            // Map the data to the known error object by the system
            val failedMappedData = ObjectMapper.mapToObject(responseData, errorModel)

            // If status is null or true means that the API succeeded otherwise it did fail
            if (failedMappedData.status != null && failedMappedData.status == false) {
                return failedMappedData
            }

            // Return null in case of no Internal Errors
            return null
        }
    }
}
