package com.ea.emiratesauction.common.network_layer.layer_dependencies

import com.ea.emiratesauction.common.network_layer.layer_dependencies.interfaces.NetworkManager
import com.ea.emiratesauction.common.network_layer.layer_dependencies.interfaces.RESTClientInterface
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class NetworkManagerImp @Inject constructor(private val retrofitBuilder: RESTClientInterface) : NetworkManager {

    companion object {
        const val TAG = "TAGRetrofit"
    }

    override suspend fun <T> callRequest(mTarget: RequestProvider<T>):ResultWrapper<T> {
       val x =  retrofitBuilder.callRequest<T>(mTarget)
        return x
    }


}
