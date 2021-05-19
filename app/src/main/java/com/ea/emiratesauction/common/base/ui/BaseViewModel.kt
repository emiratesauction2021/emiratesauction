package com.ea.emiratesauction.common.base.ui

import androidx.lifecycle.ViewModel
import com.ea.emiratesauction.data.datasource.api.model.ResponseErrorCode
import com.ea.emiratesauction.network_layer.ResultWrapper

abstract class BaseViewModel : ViewModel() {


    fun ValidateError(error:ResultWrapper.Fail): ResultWrapper.Fail?{
        when(error.error.errorCode){

            ResponseErrorCode.INTERNAL_NO_RESPONSE,
                ResponseErrorCode.INTERNAL_INTERNET_CONNECTION_LOST->{
                    return error
                }

            ResponseErrorCode.NO_CONNECTION_ERROR ->{
                // Handle it
                return null
            }

            ResponseErrorCode.NOT_FOUND ->{
                // Handle it
                return null
            }
            ResponseErrorCode.SERVER_NOT_REACHABLE ->{
                // Handle it
                return null
            }
            ResponseErrorCode.UNAUTHORIZED ->{
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