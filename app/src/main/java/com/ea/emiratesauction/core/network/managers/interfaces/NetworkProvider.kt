package com.ea.emiratesauction.core.network.managers.interfaces

import com.ea.emiratesauction.core.constants.network.NetworkRequestParametersType
import com.ea.emiratesauction.core.constants.network.RequestHTTPMethodType
import com.ea.emiratesauction.core.constants.network.RequestParameterEncoding
import com.ea.emiratesauction.core.network.request.BaseNetworkRequest
import com.ea.emiratesauction.core.network.internalError.InternalNetworkErrorInterface
import com.ea.emiratesauction.core.network.result.RequestResult
import com.ea.emiratesauction.core.utilities.network.NetworkValidator
import java.io.Serializable


interface NetworkProvider {

    suspend fun <T : Serializable, E : InternalNetworkErrorInterface> request(
            request: BaseNetworkRequest,
            successModel: Class<T>,
            errorModel: Class<E>
    ): RequestResult<T, E>

    suspend fun <T : Serializable> request(
            request: BaseNetworkRequest,
            successModel: Class<T>
    ): RequestResult<T, InternalNetworkErrorInterface>



    interface ClientProvider:NetworkProvider




    abstract class NetworkLayerProvider(val clientProvider:NetworkProvider):NetworkProvider{

        private fun CheckNetwork(parametersType: NetworkRequestParametersType, method: RequestHTTPMethodType, encoding: RequestParameterEncoding){
            NetworkValidator.httpMethodValidator(parametersType,method,encoding)
        }

        override suspend fun <T : Serializable, E : InternalNetworkErrorInterface> request(
            request: BaseNetworkRequest,
            successModel: Class<T>,
            errorModel: Class<E>
        ): RequestResult<T, E>{
            CheckNetwork(request.parameters,request.httpMethod,request.encoding)
            return clientProvider.request(request,successModel,errorModel)
        }

        override  suspend fun <T : Serializable> request(
            request: BaseNetworkRequest,
            successModel: Class<T>
        ): RequestResult<T, InternalNetworkErrorInterface>{
            CheckNetwork(request.parameters,request.httpMethod,request.encoding)
            return clientProvider.request(request,successModel)
        }


    }

}




