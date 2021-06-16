package com.ea.emiratesauction.core.common.base.ui

import android.os.Bundle
import android.os.PersistableBundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.lifecycle.lifecycleScope
import com.ea.emiratesauction.R
import com.ea.emiratesauction.core.constants.loadingIndicators.LoadingIndicatorsTypes
import com.ea.emiratesauction.core.constants.network.errors.NetworkErrors
import com.ea.emiratesauction.features.test_toBeDeleted.network.ui.PupularPeopleListFragment
import kotlinx.coroutines.Dispatchers.IO
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch
import java.util.*


@ExperimentalCoroutinesApi
abstract class BaseActivity : AppCompatActivity(), BaseRetryActionCallback {


    private lateinit var baseViewModel: BaseViewModel

    fun setViewModel(viewModel: BaseViewModel) {
        baseViewModel = viewModel
    }

//    lateinit var progressDialog: LoadingDialog
//    lateinit var noConnectionDialog: NoConnectionDialog
//    lateinit var serverDownDialog: ServerDownDialog


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_base)
        loadResourceConfiguration()
        replaceFragment(PupularPeopleListFragment())
    }

    override fun onBackPressed() {
        (supportFragmentManager
            .findFragmentById(R.id.fragmentContainer) as BaseFragment).onBackPressed()
        super.onBackPressed()
    }

    private fun replaceFragment(fragment: Fragment) {
        supportFragmentManager.beginTransaction()
            .replace(R.id.fragmentContainer, fragment)
            .commit()
    }


    fun showProgress(loadingType: LoadingIndicatorsTypes) {
        when (loadingType) {
            LoadingIndicatorsTypes.BLOCKED_SCREEN -> {
                Log.d("BaseActivity", "showProgress: ")
            }
            LoadingIndicatorsTypes.PROGRESS_BAR -> {
                Log.d("BaseActivity", "showProgress: ")
            }
        }
//        if (!this::progressDialog.isInitialized)
//            progressDialog = LoadingDialog(this);
//        progressDialog.showDialog()
    }

    fun hidProgress(loadingType: LoadingIndicatorsTypes) {
        when (loadingType) {
            LoadingIndicatorsTypes.BLOCKED_SCREEN -> {
                Log.d("BaseActivity", "hideProgress: ")
            }
            LoadingIndicatorsTypes.PROGRESS_BAR -> {
                Log.d("BaseActivity", "hideProgress: ")
            }
        }
//        if (!this::progressDialog.isInitialized)
//            progressDialog = LoadingDialog(this);
//        progressDialog.hideDialog()
    }


    fun showNoConnectionDialog() {
        Log.d("BaseActivity", "showNoConnectionDialog: ")
//        if (!this::noConnectionDialog.isInitialized)
//            noConnectionDialog = NoConnectionDialog(this, this);
//        noConnectionDialog.showDialog()
    }

    fun hideNoConnectionDialog() {
        Log.d("BaseActivity", "hideNoConnectionDialog: ")
//        if(this::noConnectionDialog.isInitialized)
//            noConnectionDialog.hideDialog()
    }

    //check ios
    fun showServerErrorDialog() {
        Log.d("BaseActivity", "showServerDownDialog: ")
//        if (!this::serverDownDialog.isInitialized)
//            serverDownDialog = ServerDownDialog(this, this);
//        serverDownDialog.showDialog()
    }


    fun hideServerErrorDialog() {
        Log.d("BaseActivity", "hideServerDownDialog: ")
        //  serverDownDialog.hideDialog()
    }


    override fun onResume() {
        super.onResume()
        observeBaseViewModel()
    }


    private fun observeBaseViewModel() {
        lifecycleScope.launch(IO) {
            baseViewModel?.let {
                manageLoadingStates(it)
                manageNetworkErrorsStates(it)
            }
        }
    }

    private suspend fun manageNetworkErrorsStates(it: BaseViewModel) {
        it.networkStates.collect { states ->
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

    override fun onCreate(savedInstanceState: Bundle?, persistentState: PersistableBundle?) {
        super.onCreate(savedInstanceState, persistentState)
        loadResourceConfiguration()
    }

    //TODO("To Be Enhanced to avoid Deprecation")
    @Suppress("DEPRECATION")
    fun loadResourceConfiguration() {
        val dm = resources.displayMetrics
        val conf = resources.configuration
        val lang = "EN" //TODO("will be Replaced by App Language")
        conf.setLocale(Locale(lang))
        resources.updateConfiguration(conf, dm)
    }
}