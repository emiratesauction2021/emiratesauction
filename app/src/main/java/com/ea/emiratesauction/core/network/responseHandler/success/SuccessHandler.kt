package com.ea.emiratesauction.core.network.responseHandler.success

import com.ea.emiratesauction.core.network.internalError.InternalNetworkErrorInterface
import com.ea.emiratesauction.core.network.objectMapper.ObjectMapper
import com.ea.emiratesauction.core.network.responseHandler.failure.ErrorHandler
import com.ea.emiratesauction.core.network.result.RequestResult
import com.ea.emiratesauction.core.network.networkErrors.NetworkErrors
import java.io.Serializable

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
                        RequestResult.Fail(NetworkErrors.INTERNAL_REQUEST_ERROR, error)
                    }
                }
            } else {
                return RequestResult.Fail(NetworkErrors.NO_RESPONSE, null)
            }
        }
    }
}