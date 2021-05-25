package com.ea.emiratesauction.core.socket.socket_manager

interface SocketListenerInterface {
    fun <T> getSocketData(data:T)
}