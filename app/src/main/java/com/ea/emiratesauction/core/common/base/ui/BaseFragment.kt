package com.ea.emiratesauction.core.common.base.ui

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.lifecycleScope
import com.ea.emiratesauction.core.constants.loadingIndicators.LoadingIndicatorsTypes
import com.ea.emiratesauction.core.constants.network.errors.NetworkErrors
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch


@ExperimentalCoroutinesApi
abstract class BaseFragment : Fragment(), BaseRetryActionCallback {
    abstract fun layoutId(): Int
    abstract fun subscribeObservers()

    lateinit var baseViewModel: BaseViewModel
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

    fun setViewModel(baseViewModel: BaseViewModel){
        this.baseViewModel = baseViewModel
    }

    open fun onBackPressed() {}
    open fun showProgress(loadingType: LoadingIndicatorsTypes) {
        when (loadingType) {
            LoadingIndicatorsTypes.BLOCKED_SCREEN -> {
                Log.d("BaseFragment", "showProgress: ")
            }
            LoadingIndicatorsTypes.PROGRESS_BAR -> {
                Log.d("BaseFragment", "showProgress: ")
            }
        }
//        if (!this::progressDialog.isInitialized)
//            progressDialog = LoadingDialog(this);
//        progressDialog.showDialog()
    }

    open fun hidProgress(loadingType: LoadingIndicatorsTypes) {
        when (loadingType) {
            LoadingIndicatorsTypes.BLOCKED_SCREEN -> {
                Log.d("BaseFragment", "hideProgress: ")
            }
            LoadingIndicatorsTypes.PROGRESS_BAR -> {
                Log.d("BaseFragment", "hideProgress: ")
            }
        }
//        if (!this::progressDialog.isInitialized)
//            progressDialog = LoadingDialog(this);
//        progressDialog.hideDialog()
    }


    fun showNoConnectionDialog() {
        Log.d("BaseFragment", "showNoConnectionDialog: ")
//        if (!this::noConnectionDialog.isInitialized)
//            noConnectionDialog = NoConnectionDialog(this, this);
//        noConnectionDialog.showDialog()
    }


    fun hideNoConnectionDialog() {
        Log.d("BaseFragment", "hideNoConnectionDialog: ")
//        if(this::noConnectionDialog.isInitialized)
//            noConnectionDialog.hideDialog()
    }

    fun showServerErrorDialog() {
        Log.d("BaseFragment", "showServerDownDialog: ")
//        if (!this::serverDownDialog.isInitialized)
//            serverDownDialog = ServerDownDialog(this, this);
//        serverDownDialog.showDialog()
    }


    fun hideServerErrorDialog() {
        Log.d("BaseFragment", "hideServerDownDialog: ")
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
                NetworkErrors.NO_INTERNET_CONNECTION -> {
                    showNoConnectionDialog()
                }
                NetworkErrors.SERVER_NOT_REACHABLE -> {
                    showServerErrorDialog()
                }
                NetworkErrors.UNAUTHORIZED -> {
                    //TODO: show Login Screen
                }
                else -> {
                }
            }
        }
    }

    open suspend fun manageLoadingStates(it: BaseViewModel) {
        it.showLoading.collect { bool ->
            when (bool) {
                true -> showProgress(LoadingIndicatorsTypes.PROGRESS_BAR)
                false -> hidProgress(LoadingIndicatorsTypes.PROGRESS_BAR)
            }
        }
    }
}