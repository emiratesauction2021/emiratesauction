package com.ea.emiratesauction.core.common.base.ui

import android.os.Bundle
import android.os.LocaleList
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.lifecycleScope
import com.ea.emiratesauction.core.network.internalError.interfaces.NetworkErrorStates
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import java.util.*


@ExperimentalCoroutinesApi
abstract class BaseFragment : Fragment(), BaseRetryActionCallback {
    abstract fun layoutId(): Int
    abstract fun subscribeObservers()

    abstract var baseViewModel: BaseViewModel?
    final val TAG = "BaseFragment"
//    lateinit var progressDialog: LoadingDialog
//    lateinit var noConnectionDialog: NoConnectionDialog
//    lateinit var serverDownDialog: ServerDownDialog


    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View = inflater.inflate(layoutId(), container, false)

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        subscribeObservers()
    }


    open fun onBackPressed() {}

    fun showProgress() {
        Log.d(TAG, "showProgress: ")
//        if (!this::progessDialog.isInitialized)
//            progessDialog = LoadingDialog(this);
//        progessDialog.showDialog()
    }

    fun hidProgress() {
        Log.d(TAG, "hidProgress: ")
//        if(this::progessDialog.isInitialized){
//            progessDialog.hideDialog()
//        }
    }


    fun showNoConnectionDialog() {
        Log.d(TAG, "showNoConnectionDialog: ")
//        if (!this::noConnectionDialog.isInitialized)
//            noConnectionDialog = NoConnectionDialog(this, this);
//        noConnectionDialog.showDialog()
    }


    fun hideNoConnectionDialog() {
        Log.d(TAG, "hideNoConnectionDialog: ")
//        if(this::noConnectionDialog.isInitialized)
//            noConnectionDialog.hideDialog()
    }

    fun showServerDownDialog() {
        Log.d(TAG, "showServerDownDialog: ")
//        if (!this::serverDownDialog.isInitialized)
//            serverDownDialog = ServerDownDialog(this, this);
//        serverDownDialog.showDialog()
    }


    fun hideServerDownDialog() {
        Log.d(TAG, "hideServerDownDialog: ")
        //  serverDownDialog.hideDialog()
    }


    override fun onResume() {
        super.onResume()
        observeBaseViewModel()
    }

    private fun observeBaseViewModel() {

        lifecycleScope.launch(Dispatchers.Main) {

            baseViewModel?.let {
                manageLoadingStates(it)
                manageNetworkErrorsStates(it)
            }
        }
    }

    private suspend fun manageNetworkErrorsStates(it: BaseViewModel) {
        it.networkStates?.collect { states ->
            when (states) {
                is NetworkErrorStates.NoInternetConnection -> {
                    hidProgress()
                    showNoConnectionDialog()
                }
                is NetworkErrorStates.ServerNotReachable -> {
                    hidProgress()
                    showServerDownDialog()
                }
                is NetworkErrorStates.UnAuthorized -> {
                    hidProgress()
                    //TODO: show Login Screen
                }
                else -> {
                }
            }
        }
    }

    private suspend fun manageLoadingStates(it: BaseViewModel) {
        it.showLoading?.collect { bool ->
            when (bool) {
                true -> showProgress()
                false -> hidProgress()
            }
        }
    }
}