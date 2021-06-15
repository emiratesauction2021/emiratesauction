package com.ea.emiratesauction.core.network.internalError.interfaces

sealed class NetworkErrorStates {
    data class UnAuthorized(val bool: Boolean) : NetworkErrorStates()
    data class ServerNotReachable(val bool: Boolean) : NetworkErrorStates()
    data class NoInternetConnection(val bool: Boolean) : NetworkErrorStates()
    object Empty : NetworkErrorStates()
}
