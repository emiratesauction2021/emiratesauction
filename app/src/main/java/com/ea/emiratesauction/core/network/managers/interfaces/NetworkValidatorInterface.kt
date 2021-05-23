package com.ea.emiratesauction.core.network.managers.interfaces

import com.ea.emiratesauction.core.constants.network.NetworkRequestParametersType
import com.ea.emiratesauction.core.constants.network.RequestHTTPMethodType
import com.ea.emiratesauction.core.constants.network.RequestParameterEncoding

interface NetworkValidatorInterface {
    fun httpMethodValidator(parametersType: NetworkRequestParametersType, method: RequestHTTPMethodType, encoding: RequestParameterEncoding)
}