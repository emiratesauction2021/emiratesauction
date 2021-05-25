package com.ea.emiratesauction.common.socket.socket_manager

import com.ea.emiratesauction.common.socket.SocketChannel
import com.ea.emiratesauction.common.socket.socket_client.SocketClientInterface

interface SocketManagerInterface {
    fun startListen()
    fun stopListen()
    fun addListener(channel: SocketChannel, mSocketListener: SocketListenerInterface)
    fun removeListener(channel: SocketChannel, mSocketListener: SocketListenerInterface)
    fun getResult(channel: SocketChannel)
    // emit

}