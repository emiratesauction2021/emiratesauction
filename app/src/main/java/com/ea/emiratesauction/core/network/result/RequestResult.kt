package com.ea.emiratesauction.core.network.result

import com.ea.emiratesauction.core.network.internalError.interfaces.InternalNetworkErrorInterface
import com.ea.emiratesauction.core.network.internalError.errors.NetworkError
import java.io.Serializable

/**
 * A wrapper for the result passed from the network layer to the upper layers
 *
 * @param T The success model
 *
 * @param E The failure model
 */
sealed class RequestResult<out T: Serializable,out E: InternalNetworkErrorInterface>{
    /**
     * The success result wrapper in case of no HTTP or internal errors
     *
     * @param T The success class which will be used to map the successful response - it will be based on the T generic object
     * which has to implement Serializable
     */
    data class Success<T:Serializable>(val value: T) : RequestResult<T, Nothing>()

    /**
     * The fail result wrapper in case of HTTP or internal errors
     *
     * @param E The error class which will be used to map the failed response - it will be based on the E generic object
     * which has to implement InternalNetworkErrorInterface
     */
    data class Fail<E: InternalNetworkErrorInterface>(val networkError: NetworkError<E>) : RequestResult<Nothing, E>()
}
