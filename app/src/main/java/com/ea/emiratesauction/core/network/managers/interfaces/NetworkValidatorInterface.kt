package com.ea.emiratesauction.core.network.managers.interfaces

import com.ea.emiratesauction.core.constants.network.NetworkRequestParametersType
import com.ea.emiratesauction.core.constants.network.RequestHTTPMethodType
import com.ea.emiratesauction.core.constants.network.RequestParameterEncoding

/**
 * this interface is needs for make check on request target
 * @see NetworkRequestParametersType
 * in case you will need to make custom network Manager
 */
interface NetworkValidatorInterface {
    fun httpMethodValidator(parametersType: NetworkRequestParametersType, method: RequestHTTPMethodType, encoding: RequestParameterEncoding)
}