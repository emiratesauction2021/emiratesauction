package com.ea.emiratesauction.core.socket.socket_client

import com.ea.emiratesauction.core.socket.socket_manager.SocketListenerInterface

interface SocketClientInterface  {
    fun startListening()
    fun stopListening()
    fun subscribeToChannel(channel:String)
    fun unSubscribeToChannel(channel:String)
    fun getResult(mSocketListener: SocketListenerInterface)
}