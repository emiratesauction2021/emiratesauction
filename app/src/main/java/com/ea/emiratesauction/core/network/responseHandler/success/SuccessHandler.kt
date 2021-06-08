package com.ea.emiratesauction.core.network.responseHandler.success

import com.ea.emiratesauction.core.network.internalError.interfaces.InternalNetworkErrorInterface
import com.ea.emiratesauction.core.network.internalError.errors.NetworkError
import com.ea.emiratesauction.core.network.objectMapper.ObjectMapper
import com.ea.emiratesauction.core.network.responseHandler.failure.ErrorHandler
import com.ea.emiratesauction.core.network.result.RequestResult
import com.ea.emiratesauction.core.constants.network.errors.NetworkErrors
import java.io.Serializable

/**
 * Handles all the succeeded responses across the app, and checks on the internal errors as well
 *
 * The implementation of the success handling is decoupled from the providers in order to unify the implementation regardless of the used provider
 */
class SuccessHandler {
    companion object {
        /**
         * Maps the successful response to the passed T model in case of no internal errors found
         *
         * In case of internal error, the error is mapped to the E model
         *
         * @param responseData The raw response data
         *
         * @param successModel The success class which will be used to map the successful response - it will be based on the T generic object
         * which has to implement Serializable
         *
         * @param errorModel The error class which will be used to map the failed response - it will be based on the E generic object
         * which has to implement InternalNetworkErrorInterface
         *
         */
        fun <T : Serializable,E : InternalNetworkErrorInterface> mapSuccessfulResponse(responseData: Any?, successModel: Class<T>, errorModel: Class<E>):
                RequestResult<T, E> {

            // Check on the response data
            if(responseData != null) {
                responseData.let {
                    //******************************************************
                    //-------------- Check for the internal errors before mapping to the success model ---------
                    val error = ErrorHandler.checkForInternalError(responseData, errorModel)
                    return if(error == null) {
                        //-------------- Map to the success model in case of no internal errors ---------
                        val successMappedData = ObjectMapper.mapToObject(responseData, successModel)
                        RequestResult.Success(successMappedData)
                    } else {
                        //-------------- Pass the error back in case of internal errors --------
                        RequestResult.Fail(NetworkError(NetworkErrors.INTERNAL_REQUEST_ERROR, error))
                    }
                }
            } else {
                // Response data isn't available, corrupted, or null
                return RequestResult.Fail(NetworkError(NetworkErrors.NO_RESPONSE, null))
            }
        }
    }
}