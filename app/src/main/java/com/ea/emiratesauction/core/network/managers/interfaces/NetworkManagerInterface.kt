package com.ea.emiratesauction.core.network.managers.interfaces
import com.ea.emiratesauction.core.utilities.network.NetworkExceptions
import com.ea.emiratesauction.core.network.request.BaseNetworkRequest

/**
 * this layer is used for Separation between ٌ remote source control and REST client third party Libraries
 * to use Network Provider like
 * @see NetworkProviderInterface
 * first you will need to check on request target validations
 * @see BaseNetworkRequest
 * if all target settings is right it will return NetworkProviderInterface
 * else it will throw Exception of type
 * @throws NetworkExceptions
 *
 */
interface NetworkManagerInterface:NetworkValidatorInterface {

    fun validate(request: BaseNetworkRequest):NetworkProviderInterface
}