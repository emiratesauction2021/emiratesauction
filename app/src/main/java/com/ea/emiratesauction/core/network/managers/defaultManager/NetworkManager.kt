package com.ea.emiratesauction.core.network.managers.defaultManager

import com.ea.emiratesauction.core.constants.network.NetworkRequestParametersType
import com.ea.emiratesauction.core.constants.network.RequestHTTPMethodType
import com.ea.emiratesauction.core.constants.network.RequestParameterEncoding
import com.ea.emiratesauction.core.network.managers.interfaces.NetworkManagerInterface
import com.ea.emiratesauction.core.network.managers.interfaces.NetworkProviderInterface
import com.ea.emiratesauction.core.network.request.BaseNetworkRequest
import com.ea.emiratesauction.core.utilities.network.NetworkValidator

class NetworkManager(private val networkProvider: NetworkProviderInterface) :
    NetworkManagerInterface {

    override fun validate(request: BaseNetworkRequest): NetworkProviderInterface {

        this.httpMethodValidator(
            request.parameters,
            request.httpMethod,
            request.encoding
        )

        return networkProvider
    }

    override fun httpMethodValidator(
        parametersType: NetworkRequestParametersType,
        method: RequestHTTPMethodType,
        encoding: RequestParameterEncoding
    ) {
        NetworkValidator.httpMethodValidator(
            parametersType,
            method,
            encoding
        )
    }

}