package com.ea.emiratesauction.core.network.internalError

import com.ea.emiratesauction.core.constants.network.NetworkErrors

class NetworkError <E: InternalNetworkErrorInterface>(var errorType: NetworkErrors, var error: E?) {

}