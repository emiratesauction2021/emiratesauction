package com.ea.emiratesauction.core.network.managers.interfaces

import com.ea.emiratesauction.core.network.request.BaseNetworkRequest
import com.ea.emiratesauction.core.network.internalError.InternalNetworkErrorInterface
import com.ea.emiratesauction.core.network.result.RequestResult
import java.io.Serializable

/**
 * after check all validations
 * @see NetworkManagerInterface
 * then you will pass request parameter
 * @see BaseNetworkRequest
 * , Response model in success case
 * and Response model in error case "it's optional"
 * @see InternalNetworkErrorInterface
 *
 */
interface NetworkProviderInterface {

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




