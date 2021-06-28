package com.ea.emiratesauction.features.test_toBeDeleted.network.requestTarget

import com.ea.emiratesauction.core.network.request.networkRequest.BaseNetworkRequest
import com.ea.emiratesauction.core.constants.network.httpMethods.RequestHTTPMethodType
import com.ea.emiratesauction.core.constants.network.hosts.Host
import com.ea.emiratesauction.core.constants.network.parameters.NetworkRequestParametersType

class PopularPeoplesRequestTarget: BaseNetworkRequest(){

    override var baseURL: Host = Host.DEFAULT_BASE

    override var endPoint: String = "networkLayerTest"

    override var parameters: NetworkRequestParametersType = NetworkRequestParametersType.Standard(hashMapOf())

    override var httpMethod: RequestHTTPMethodType = RequestHTTPMethodType.POST

}