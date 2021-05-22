package com.ea.emiratesauction.core.network.internalError

class InternalNetworkError(
    override val status: Boolean?,
    override val code: Int?,
    override val message: String?
) : InternalNetworkErrorInterface {

}