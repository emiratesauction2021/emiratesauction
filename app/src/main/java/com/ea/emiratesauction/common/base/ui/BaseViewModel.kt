package com.ea.emiratesauction.common.base.ui

import androidx.lifecycle.ViewModel
import com.ea.emiratesauction.data.datasource.api.model.InternalNetworkErrorInterface
import com.ea.emiratesauction.data.datasource.api.model.ResponseErrorCode
import com.ea.emiratesauction.network_layer.ResultWrapper

abstract class BaseViewModel : ViewModel() {


    fun <T : InternalNetworkErrorInterface> ValidateError(responseError: ResultWrapper.Fail<T>): ResultWrapper.Fail<T>? {
        when (responseError.errorHappen) {

            ResponseErrorCode.INTERNAL_SERVER_ERROR -> {

                return responseError
            }

            ResponseErrorCode.NO_CONNECTION_ERROR -> {
                // Handle it
                return null
            }

            ResponseErrorCode.NOT_FOUND -> {
                // Handle it
                return null
            }
            ResponseErrorCode.SERVER_NOT_REACHABLE -> {
                // Handle it
                return null
            }
            ResponseErrorCode.UNAUTHORIZED -> {
                // Handle it
                return null
            }

            else -> {

                // Handle Some Error Happen

                return null
            }
        }
    }

}