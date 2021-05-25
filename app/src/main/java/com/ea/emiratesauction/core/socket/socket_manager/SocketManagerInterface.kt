package com.ea.emiratesauction.core.socket.socket_manager

import com.ea.emiratesauction.core.socket.SocketChannel

interface SocketManagerInterface {
    fun startListen()
    fun stopListen()
    fun addListener(channel: SocketChannel, mSocketListener: SocketListenerInterface)
    fun removeListener(channel: SocketChannel, mSocketListener: SocketListenerInterface)
    fun getResult(channel: SocketChannel)
    // emit

}