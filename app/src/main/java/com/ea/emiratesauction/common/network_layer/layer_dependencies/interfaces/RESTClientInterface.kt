package com.ea.emiratesauction.common.network_layer.layer_dependencies.interfaces

import com.ea.emiratesauction.common.network_layer.layer_dependencies.RequestProvider
import com.ea.emiratesauction.common.network_layer.layer_dependencies.ResultWrapper

interface RESTClientInterface {
    suspend   fun<T> callRequest(provider: RequestProvider<T>):ResultWrapper<T>

}