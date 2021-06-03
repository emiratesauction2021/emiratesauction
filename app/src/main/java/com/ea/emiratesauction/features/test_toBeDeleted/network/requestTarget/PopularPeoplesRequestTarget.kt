package com.ea.emiratesauction.features.test_toBeDeleted.network.requestTarget

import com.ea.emiratesauction.core.network.request.BaseNetworkRequest
import com.ea.emiratesauction.core.constants.network.RequestHTTPMethodType
import com.ea.emiratesauction.core.constants.network.NetworkRequestParametersType
import com.ea.emiratesauction.core.constants.network.Host

class PopularPeoplesRequestTarget: BaseNetworkRequest(){

    override var baseURL: Host = Host.DEFAULT_BASE

    override var endPoint: String = "networkLayerTest"

    override var parameters: NetworkRequestParametersType = NetworkRequestParametersType.Standard(hashMapOf())

    override var httpMethod: RequestHTTPMethodType = RequestHTTPMethodType.POST

}