package com.ea.emiratesauction.core.network.managers.interfaces

import com.ea.emiratesauction.core.network.request.BaseNetworkRequest

interface NetworkManagerInterface:NetworkValidatorInterface {

    fun validate(request: BaseNetworkRequest):NetworkProviderInterface
}