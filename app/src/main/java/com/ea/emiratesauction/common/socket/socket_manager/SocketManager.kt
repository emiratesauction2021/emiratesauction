package com.ea.emiratesauction.common.socket.socket_manager


import com.ea.emiratesauction.common.socket.SocketChannel
import com.ea.emiratesauction.common.socket.socket_client.SocketClientInterface
import kotlin.collections.ArrayList

object SocketManager : SocketManagerInterface {

    private var isStartingListen = false
    private lateinit var socketClient: SocketClientInterface
    var socketListeners = mutableMapOf<String, ArrayList<SocketListenerInterface>>()

    init {
    }

    override fun startListen() {
        if (!isStartingListen) {
            socketClient.startListening()
            isStartingListen = true
        }
    }

    override fun stopListen() {
        if (isStartingListen) {
            socketClient.stopListening()
            isStartingListen = false
        }
    }

    override fun setSocketClient(socketClientInterface: SocketClientInterface) {
        stopListen()
        this.socketClient = socketClientInterface
    }

    override fun addListener(channel: SocketChannel, mSocketListener: SocketListenerInterface) {

        if (socketListeners.get(channel.name) != null) { // if channel is found
            socketListeners.get(channel.name)!!.add(mSocketListener)
            //
        } else { // if there is no channels found any more
            socketListeners.put(channel.name, arrayListOf(mSocketListener))
            socketClient.subscribeToChannel(channel.name)
            getResult(channel)

        }

    }

    override fun removeListener(channel: SocketChannel, mSocketListener: SocketListenerInterface) {
        if (socketListeners.get(channel.name) != null) {
            if (socketListeners.get(channel.name)!!.size > 0 && socketListeners.get(channel.name)!!.contains(mSocketListener))
                socketListeners.get(channel.name)!!.remove(mSocketListener)
            else
                socketClient.unSubscribeToChannel(channel.name)
        }
    }

    override fun getResult(channel: SocketChannel) {
        socketClient.getResult(object : SocketListenerInterface {
            override fun <T> getSocketData(data: T) {
                if (socketListeners.get(channel.name) != null && socketListeners.get(
                        channel.name
                    )!!.size > 0
                ) {
                    socketListeners!!.get(channel.name)!!.forEach {
                        it.getSocketData(data)
                    }
                }
            }

        })

    }

}




