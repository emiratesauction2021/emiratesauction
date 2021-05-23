package com.ea.emiratesauction.core.network.managers.interfaces

import com.ea.emiratesauction.core.network.request.BaseNetworkRequest
import com.ea.emiratesauction.core.network.internalError.InternalNetworkErrorInterface
import com.ea.emiratesauction.core.network.result.RequestResult
import java.io.Serializable


interface NetworkProvider : NetworkValidatorInterface {

    suspend fun <T : Serializable, E : InternalNetworkErrorInterface> request(
            request: BaseNetworkRequest,
            successModel: Class<T>,
            errorModel: Class<E>
    ): RequestResult<T, E>

    suspend fun <T : Serializable> request(
            request: BaseNetworkRequest,
            successModel: Class<T>
    ): RequestResult<T, InternalNetworkErrorInterface>

}