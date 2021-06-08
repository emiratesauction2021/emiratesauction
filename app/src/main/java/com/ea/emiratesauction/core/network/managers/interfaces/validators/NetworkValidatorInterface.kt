package com.ea.emiratesauction.core.network.managers.interfaces.validators

import com.ea.emiratesauction.core.constants.network.NetworkRequestParametersType
import com.ea.emiratesauction.core.constants.network.httpMethods.RequestHTTPMethodType
import com.ea.emiratesauction.core.constants.network.encodings.RequestParameterEncoding

/**
 * Defines the network validation methods that every manager has to implement before processing the network request
 */
interface NetworkValidatorInterface {
    /**
     * Validate the network request based on specific set of parameters and throws an exception in case that one of the validation has failed
     *
     * @param parametersType The request parameter type -> Standard or Composite
     *
     * @param method The used HTTP method within the request
     *
     * @param encoding The used encoding in the request
     *
     * @throws Exception When the validation of the parameters fails
     */
    fun httpMethodValidator(parametersType: NetworkRequestParametersType, method: RequestHTTPMethodType, encoding: RequestParameterEncoding)
}