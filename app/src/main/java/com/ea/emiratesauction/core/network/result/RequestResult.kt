package com.ea.emiratesauction.core.network.result

import com.ea.emiratesauction.core.network.internalError.InternalNetworkErrorInterface
import com.ea.emiratesauction.core.network.internalError.NetworkError
import java.io.Serializable

/**
 * after call Api and return data in case of
 * - fail in HTTP call "External Error" will return
 * @see Fail with Error model
 * @param E
 * - Success there is two condition
 *     $ response is success
 *     $ response is fail "Internal Error"
 *   In both cases it will back in
 * @see Success with Success model
 * @param T
 */
sealed class RequestResult<out T: Serializable,out E: InternalNetworkErrorInterface>{
    data class Success<T:Serializable>(val value: T) : RequestResult<T, Nothing>()

    data class Fail<E: InternalNetworkErrorInterface>(val networkError: NetworkError<E>) : RequestResult<Nothing, E>()
}


