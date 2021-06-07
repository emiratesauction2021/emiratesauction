package com.ea.emiratesauction.core.network.internalError

/**
 * The pre-defined internal network error used in the app between the network layer and the features
 */
class InternalNetworkError(
    override val status: Boolean?,
    override val code: Int?,
    override val message: String?
) : InternalNetworkErrorInterface {

}