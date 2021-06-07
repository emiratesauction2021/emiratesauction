package com.ea.emiratesauction.core.network.internalError

import com.ea.emiratesauction.core.constants.network.NetworkErrors

/**
 * The network error which is passed from the network layer to the upper layers
 *
 * @param errorType The error type from the NetworkErrors enum
 *
 * @param error The error object which will be parsed based on the E generic object which has to implement InternalNetworkErrorInterface
 */
class NetworkError <E: InternalNetworkErrorInterface>(var errorType: NetworkErrors, var error: E?)