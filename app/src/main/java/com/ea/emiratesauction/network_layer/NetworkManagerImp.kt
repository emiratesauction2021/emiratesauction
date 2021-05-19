package com.ea.emiratesauction.network_layer

import com.ea.emiratesauction.common.base.domain.RequestTarget
import com.ea.emiratesauction.network_layer.interfaces.NetworkManager
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class NetworkManagerImp @Inject constructor(private val rectClientBuilder: NetworkManager) : NetworkManager {

    companion object {
        const val TAG = "TAGRetrofit"
    }

    override suspend fun <T> callRequest(mTarget: RequestTarget<T>): ResultWrapper<T> {
       return rectClientBuilder.callRequest<T>(mTarget)

    }


}
