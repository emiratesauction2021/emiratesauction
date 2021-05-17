package com.ea.emiratesauction.data.network

import com.ea.emiratesauction.common.network_layer.layer_dependencies.interfaces.NetworkManager
import com.ea.emiratesauction.domain.models.CreateSessionReqBody
import com.ea.emiratesauction.domain.models.CreateSessionResponse
import com.ea.emiratesauction.domain.reprositories.CreateSessionRepo
import com.ea.emiratesauction.common.utils.ApiEndPoints

class CreateSessionRepoImp(val networkManager : NetworkManager): CreateSessionRepo {

    override suspend fun createNewSession(requestBody: CreateSessionReqBody): CreateSessionResponse? {
       val headersMap = hashMapOf<String, Any>()
        headersMap["Accept-Language"] = "en"
        headersMap["accept"] = "application/json"
        headersMap["Content-Type"] = "application/json-patch+json"
//        return networkManager.callPOSTRequest(
//                ApiEndPoints.CREATE_SESSION_ENDPOINT_URL+"?api_key="+ ApiEndPoints.API_KEY
//                  , headersMap ,
//              requestBody, CreateSessionResponse::class.java)
        return null
    }
}