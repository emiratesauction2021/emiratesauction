package com.ea.emiratesauction.common.network_layer.layer_dependencies.interfaces

import com.ea.emiratesauction.common.network_layer.layer_dependencies.RequestMethod
import com.ea.emiratesauction.common.network_layer.layer_dependencies.RequestProvider
import com.ea.emiratesauction.common.network_layer.layer_dependencies.ResultWrapper


interface NetworkManager {

    suspend fun<T> callRequest(mTarget: RequestProvider<T>):ResultWrapper<T>

}