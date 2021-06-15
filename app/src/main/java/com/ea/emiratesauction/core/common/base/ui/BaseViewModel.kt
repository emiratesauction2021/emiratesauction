package com.ea.emiratesauction.core.common.base.ui

import android.util.Log
import androidx.lifecycle.ViewModel
import com.ea.emiratesauction.core.constants.network.errors.NetworkErrors
import com.ea.emiratesauction.core.network.internalError.interfaces.InternalNetworkErrorInterface
import com.ea.emiratesauction.core.network.internalError.interfaces.NetworkErrorStates
import com.ea.emiratesauction.core.network.internalError.interfaces.NetworkErrorStates.*
import com.ea.emiratesauction.core.network.result.RequestResult
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.MutableStateFlow


@ExperimentalCoroutinesApi
abstract class BaseViewModel : ViewModel() {

    private val _networkStates = MutableStateFlow<NetworkErrorStates>(Empty)
    val networkStates get() = _networkStates

    fun <E : InternalNetworkErrorInterface> validateError(responseError: RequestResult.Fail<E>): RequestResult.Fail<E>? {
        when (responseError.networkError.errorType) {

            NetworkErrors.INTERNAL_REQUEST_ERROR -> {
                return responseError
            }

            NetworkErrors.NO_INTERNET_CONNECTION -> {
                _networkStates.value = NoInternetConnection(true)
                return null
            }

            NetworkErrors.SERVER_NOT_REACHABLE -> {
                _networkStates.value = ServerNotReachable(true)
                return null
            }

            NetworkErrors.UNAUTHORIZED -> {
                _networkStates.value = UnAuthorized(true)
                return null
            }
            else -> {
                _networkStates.value = Empty
                Log.e("Base", "Error And It's Not Handled !!!")
                return null
            }
        }
    }

}