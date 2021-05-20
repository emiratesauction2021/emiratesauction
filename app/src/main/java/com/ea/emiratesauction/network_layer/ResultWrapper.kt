package com.ea.emiratesauction.network_layer

import com.ea.emiratesauction.data.datasource.api.model.InternalNetworkErrorInterface
import com.ea.emiratesauction.data.datasource.api.model.ResponseErrorCode
import java.io.Serializable

sealed class ResultWrapper<out T: Serializable,out E:InternalNetworkErrorInterface>{
    data class Success<T:Serializable>(val value: T) : ResultWrapper<T,Nothing>()

    data class Fail<E:InternalNetworkErrorInterface>(val error: E?, val errorHappen: ResponseErrorCode) : ResultWrapper<Nothing,E>()
}


