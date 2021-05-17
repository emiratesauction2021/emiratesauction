package com.ea.emiratesauction.common.network_layer.layer_dependencies

import com.ea.emiratesauction.common.utils.ApiEndPoints

class RequestProvider<T>(val responseClassType:Class<T>) {
    var EndPointUrl:String = ""
    var headersMap: Map<String, Any> = ApiEndPoints.headersMap
    var requestType: RequestMethod = RequestMethod.NONE
    var paramsType: ParamTYPE = ParamTYPE.NONE
    var requestParams = hashMapOf<String, Any>()
}