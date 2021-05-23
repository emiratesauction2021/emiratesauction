package com.ea.emiratesauction.core.network.result

import com.ea.emiratesauction.core.network.internalError.InternalNetworkErrorInterface
import com.ea.emiratesauction.core.network.internalError.NetworkError
import java.io.Serializable

sealed class RequestResult<out T: Serializable,out E: InternalNetworkErrorInterface>{
    data class Success<T:Serializable>(val value: T) : RequestResult<T, Nothing>()

    data class Fail<E: InternalNetworkErrorInterface>(val networkError: NetworkError<E>) : RequestResult<Nothing, E>()
}


