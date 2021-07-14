package com.ea.emiratesauction.core.common.base.ui

import android.util.Log
import androidx.lifecycle.ViewModel
import com.ea.emiratesauction.core.constants.network.errors.NetworkErrors
import com.ea.emiratesauction.core.network.internalError.interfaces.InternalNetworkErrorInterface
import com.ea.emiratesauction.core.network.result.RequestResult
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.MutableStateFlow

/*
* BaseViewModel it will be across the all viewModels to hold the common states like network errors and loading
* */
@ExperimentalCoroutinesApi
abstract class BaseViewModel : ViewModel() {

    /*
    * @property _networkStates flow  which emit Error states from NetworkErrorStates sealed class
    * @property networkStates flow takes the value from _networkStates and all view will observe the change states
    * */
    private val _networkStates = MutableStateFlow(NetworkErrors.EMPTY)
    val networkStates get() = _networkStates

    /*
    * @property showLoading it's a bool flow that will for the view to show or hide the progressbar
    * any viewModel can set the value it will be reachable from the base fragment and activity
    * */
    val showLoading = MutableStateFlow(false)


    /*
    * Method validateError to validate any kind of network error
    * @param responseError pass it from api response error to detect fail status
    * @enum NetworkErrors hold all Network issues type  & based on the type pass it to {
    *   _networkStates.value = ()
    * }
    * */
    fun <E : InternalNetworkErrorInterface> validateError(responseError: RequestResult.Fail<E>): RequestResult.Fail<E>? {
        _networkStates.value = responseError.networkError.errorType
        when (responseError.networkError.errorType) {

            NetworkErrors.INTERNAL_REQUEST_ERROR -> {
                return responseError
            }

            NetworkErrors.NO_INTERNET_CONNECTION -> {

                return null
            }

            NetworkErrors.SERVER_NOT_REACHABLE -> {

                return null
            }

            NetworkErrors.UNAUTHORIZED -> {
                return null
            }
            else -> {
                Log.e("Base", "Error And It's Not Handled !!!")
                return null
            }
        }
    }

}