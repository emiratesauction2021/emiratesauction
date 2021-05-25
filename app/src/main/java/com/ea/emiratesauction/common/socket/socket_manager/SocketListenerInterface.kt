package com.ea.emiratesauction.common.socket.socket_manager

interface SocketListenerInterface {
    fun <T> getSocketData(data:T)
}