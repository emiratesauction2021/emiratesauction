package com.ea.emiratesauction.network_layer.interfaces

import com.ea.emiratesauction.common.base.domain.RequestTarget
import com.ea.emiratesauction.network_layer.ResultWrapper


interface NetworkManager {

    suspend fun<T> callRequest(mTarget: RequestTarget<T>): ResultWrapper<T>

}