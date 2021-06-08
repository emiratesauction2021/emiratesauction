package com.ea.emiratesauction.core.network.managers.interfaces.managers
import com.ea.emiratesauction.core.network.managers.interfaces.providers.NetworkProviderInterface
import com.ea.emiratesauction.core.network.managers.interfaces.validators.NetworkValidatorInterface
import com.ea.emiratesauction.core.network.request.networkRequest.BaseNetworkRequest

/**
 * The Network Manager Interface which every network manager has to implement to be injected and used with the app
 */
interface NetworkManagerInterface: NetworkValidatorInterface {
    /**
     * Validates the passed request and it's parameters first then passes the Network Provider to do the actual request
     *
     * @param request The network request which will be fired
     *
     * @return The network provider interface which will be used to fire the request
     */
    fun validate(request: BaseNetworkRequest): NetworkProviderInterface
}