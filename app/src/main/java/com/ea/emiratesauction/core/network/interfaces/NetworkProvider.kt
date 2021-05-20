package com.ea.emiratesauction.core.network.interfaces

import com.ea.emiratesauction.common.base.domain.BaseNetworkRequest
import com.ea.emiratesauction.data.datasource.api.model.InternalNetworkErrorInterface
import com.ea.emiratesauction.network_layer.ResultWrapper
import java.io.Serializable


interface NetworkProvider {

    suspend fun <T : Serializable,E : InternalNetworkErrorInterface> callRequest(mTarget: BaseNetworkRequest,
                                                                                 successModel: Class<T>,
                                                                                 errorModel: Class<E>): ResultWrapper<T,E>

}