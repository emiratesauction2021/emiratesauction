package com.ea.emiratesauction.core.network.managers.networkManager

import com.ea.emiratesauction.core.constants.network.httpMethods.RequestHTTPMethodType
import com.ea.emiratesauction.core.constants.network.encodings.RequestParameterEncoding
import com.ea.emiratesauction.core.constants.network.parameters.NetworkRequestParametersType
import com.ea.emiratesauction.core.network.managers.interfaces.managers.NetworkManagerInterface
import com.ea.emiratesauction.core.network.managers.interfaces.providers.NetworkProviderInterface
import com.ea.emiratesauction.core.network.request.networkRequest.BaseNetworkRequest
import com.ea.emiratesauction.core.utilities.network.NetworkValidator
import javax.inject.Inject

/**
 * The network manager implementation to be used across the whole app as a single entry for the network provider
 */
class NetworkManager @Inject constructor(private val networkProvider: NetworkProviderInterface) :
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