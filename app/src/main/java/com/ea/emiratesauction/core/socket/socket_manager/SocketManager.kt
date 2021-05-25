package com.ea.emiratesauction.core.socket.socket_manager


import com.ea.emiratesauction.core.socket.SocketChannel
import com.ea.emiratesauction.core.socket.socket_client.SocketClientInterface
import javax.inject.Inject
import kotlin.collections.ArrayList

class SocketManager @Inject constructor(val socketClientInterface: SocketClientInterface) : SocketManagerInterface {

    private var isStartingListen = false
    var socketListeners = mutableMapOf<String, ArrayList<SocketListenerInterface>>()

    init {
    }

    override fun startListen() {
        if (!isStartingListen) {
            socketClientInterface.startListening()
            isStartingListen = true
        }
    }

    override fun stopListen() {
        if (isStartingListen) {
            socketClientInterface.stopListening()
            isStartingListen = false
        }
    }



    override fun addListener(channel: SocketChannel, mSocketListener: SocketListenerInterface) {

        if (socketListeners.get(channel.name) != null) { // if channel is found
            socketListeners.get(channel.name)!!.add(mSocketListener)
            //
        } else { // if there is no channels found any more
            socketListeners.put(channel.name, arrayListOf(mSocketListener))
            socketClientInterface.subscribeToChannel(channel.name)
            getResult(channel)

        }

    }

    override fun removeListener(channel: SocketChannel, mSocketListener: SocketListenerInterface) {
        if (socketListeners.get(channel.name) != null) {
            if (socketListeners.get(channel.name)!!.size > 0 && socketListeners.get(channel.name)!!.contains(mSocketListener))
                socketListeners.get(channel.name)!!.remove(mSocketListener)
            else
                socketClientInterface.unSubscribeToChannel(channel.name)
        }
    }

    override fun getResult(channel: SocketChannel) {
        socketClientInterface.getResult(object : SocketListenerInterface {
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




