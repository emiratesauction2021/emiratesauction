package com.ea.emiratesauction.core.common.base.ui

import android.util.Log
import androidx.lifecycle.ViewModel
import com.ea.emiratesauction.core.network.internalError.InternalNetworkErrorInterface
import com.ea.emiratesauction.core.constants.network.NetworkErrors
import com.ea.emiratesauction.core.network.result.RequestResult

abstract class BaseViewModel : ViewModel() {


    fun <E : InternalNetworkErrorInterface> validateError(responseError: RequestResult.Fail<E>): RequestResult.Fail<E>? {
        when (responseError.networkError.errorType) {

            NetworkErrors.INTERNAL_REQUEST_ERROR -> {
                return responseError
            }

            NetworkErrors.NO_INTERNET_CONNECTION -> {
                // Handle it
                return null
            }

            NetworkErrors.SERVER_NOT_REACHABLE -> {
                // Handle it
                return null
            }

            NetworkErrors.UNAUTHORIZED -> {
                // Handle it
                return null
            }
            else -> {

                Log.e("Base","Error And It's Not Handled !!!")
                return null
            }
        }
    }

}