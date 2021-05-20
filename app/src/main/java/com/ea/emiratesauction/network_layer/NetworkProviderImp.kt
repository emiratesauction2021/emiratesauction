package com.ea.emiratesauction.network_layer

import com.ea.emiratesauction.common.base.domain.BaseNetworkRequest
import com.ea.emiratesauction.core.network.interfaces.NetworkProvider
import com.ea.emiratesauction.data.datasource.api.model.InternalNetworkErrorInterface
import java.io.Serializable
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class NetworkProviderImp @Inject constructor(private val rectClientBuilder: NetworkProvider) : NetworkProvider {

    companion object {
        const val TAG = "TAGRetrofit"
    }

    override suspend fun <T : Serializable, E : InternalNetworkErrorInterface> callRequest(
            mTarget: BaseNetworkRequest, successModel: Class<T>,
            errorModel: Class<E>
    ): ResultWrapper<T,E> {
        return rectClientBuilder.callRequest<T,E>(mTarget, successModel,errorModel)

    }


}
