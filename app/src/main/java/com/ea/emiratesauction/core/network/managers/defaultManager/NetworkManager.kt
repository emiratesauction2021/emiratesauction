package com.ea.emiratesauction.core.network.managers.defaultManager

import com.ea.emiratesauction.common.base.domain.BaseNetworkRequest
import com.ea.emiratesauction.core.network.managers.interfaces.NetworkProvider
import com.ea.emiratesauction.core.network.internalError.InternalNetworkErrorInterface
import com.ea.emiratesauction.core.network.result.RequestResult
import java.io.Serializable
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class NetworkManager @Inject constructor(private val networkProvider: NetworkProvider) : NetworkProvider {

    companion object {
        const val TAG = "TAGRetrofit"
    }

    override suspend fun <T : Serializable, E : InternalNetworkErrorInterface> request(
        request: BaseNetworkRequest, successModel: Class<T>,
        errorModel: Class<E>
    ): RequestResult<T, E> {
        return networkProvider.request(request, successModel, errorModel)
    }

    override suspend fun <T : Serializable> request(request: BaseNetworkRequest, successModel: Class<T>): RequestResult<T, InternalNetworkErrorInterface> {
        return networkProvider.request(request, successModel)
    }


}
