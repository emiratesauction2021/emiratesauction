package com.ea.emiratesauction.core.network.managers.interfaces.providers

import com.ea.emiratesauction.core.network.request.networkRequest.BaseNetworkRequest
import com.ea.emiratesauction.core.network.internalError.interfaces.InternalNetworkErrorInterface
import com.ea.emiratesauction.core.network.result.RequestResult
import java.io.Serializable

/**
 * The network provider interface that every provider has to implement to be used in the network manager
 */
interface NetworkProviderInterface {

    /**
     * Make a request and passes the success model and the error model
     *
     * @param request The network request and all of its parameters
     *
     * @param successModel The success class which will be used to map the successful response - it will be based on the T generic object
     * which has to implement Serializable
     *
     * @param errorModel The error class which will be used to map the failed response - it will be based on the E generic object
     * which has to implement InternalNetworkErrorInterface
     *
     * @return A RequestResult class with a success model or error model based on the response from the API
     */
    suspend fun <T : Serializable, E : InternalNetworkErrorInterface> request(
            request: BaseNetworkRequest,
            successModel: Class<T>,
            errorModel: Class<E>
    ): RequestResult<T, E>

    /**
     * Make a request and passes the success model, the error model will be mapped to the InternalNetworkError automatically
     *
     * @param request The network request and all of its parameters
     *
     * @param successModel The success class which will be used to map the successful response - it will be based on the T generic object
     * which has to implement Serializable
     *
     * @return A RequestResult class with a success model or error model based on the response from the API
     */
    suspend fun <T : Serializable> request(
            request: BaseNetworkRequest,
            successModel: Class<T>
    ): RequestResult<T, InternalNetworkErrorInterface>

}




