package com.ea.emiratesauction.core.network.internalError

import com.ea.emiratesauction.core.network.networkErrors.NetworkErrors

class NetworkError <E: InternalNetworkErrorInterface>(var errorType: NetworkErrors, var error: E?) {

}