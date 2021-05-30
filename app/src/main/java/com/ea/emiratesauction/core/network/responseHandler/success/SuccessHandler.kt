package com.ea.emiratesauction.core.network.responseHandler.success

import com.ea.emiratesauction.core.network.internalError.InternalNetworkErrorInterface
import com.ea.emiratesauction.core.network.internalError.NetworkError
import com.ea.emiratesauction.core.network.objectMapper.ObjectMapper
import com.ea.emiratesauction.core.network.responseHandler.failure.ErrorHandler
import com.ea.emiratesauction.core.network.result.RequestResult
import com.ea.emiratesauction.core.constants.network.NetworkErrors
import java.io.Serializable

/**
 * this handler is to map data with return model
 * @param T and check if it success response with success data
 * if it success wil return in Success Case
 * else will return in fail case
 * @see RequestResult
 */
class SuccessHandler {
    companion object {
        fun <T : Serializable,E : InternalNetworkErrorInterface> mapSuccessfulResponse(responseData: Any?, successModel: Class<T>, errorModel: Class<E>):
                RequestResult<T, E> {

            if(responseData != null) {
                responseData.let {
                    val error = ErrorHandler.checkForInternalError(responseData, errorModel)
                    return if(error == null) {
                        val successMappedData = ObjectMapper.mapToObject(responseData, successModel)
                        RequestResult.Success(successMappedData)
                    } else {
                        RequestResult.Fail(NetworkError(NetworkErrors.INTERNAL_REQUEST_ERROR, error))
                    }
                }
            } else {
                return RequestResult.Fail(NetworkError(NetworkErrors.NO_RESPONSE, null))
            }
        }
    }
}