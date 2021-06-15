package com.ea.emiratesauction.core.common.base.ui

import android.content.Context
import android.net.ConnectivityManager
import android.os.Bundle
import android.os.PersistableBundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.lifecycleScope
import com.ea.emiratesauction.R
import com.ea.emiratesauction.core.network.internalError.interfaces.NetworkErrorStates
import kotlinx.coroutines.Dispatchers.IO
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch
import java.util.*


abstract class BaseActivity : AppCompatActivity(), BaseRetryActionCallback {


    abstract var baseViewModel: BaseViewModel?
    final  val TAG = "BaseActivity -> "
//    lateinit var progressDialog: LoadingDialog
//    lateinit var noConnectionDialog: NoConnectionDialog
//    lateinit var serverDownDialog: ServerDownDialog


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_base)
        replaceFragment(savedInstanceState)
    }

    override fun onBackPressed() {
        (supportFragmentManager
            .findFragmentById(R.id.fragmentContainer) as BaseFragment).onBackPressed()
        super.onBackPressed()
    }

    private fun replaceFragment(savedInstanceState: Bundle?) =
        supportFragmentManager.beginTransaction()
            .replace(R.id.fragmentContainer, fragment())
            .commit()

    abstract fun fragment(): BaseFragment


    fun showProgress() {
        Log.d(TAG, "showProgress: ")
//        if (!this::progressDialog.isInitialized)
//            progressDialog = LoadingDialog(this);
//        progressDialog.showDialog()
    }

    fun hidProgress() {
        Log.d(TAG, "hidProgress: ")
//        if(this::progressDialog.isInitialized){
//            progressDialog.hideDialog()
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

    /*
    *  it.successMessage.observe(this, Observer {
                    hidProgress()
                })
                it.errorMessage.observe(this, Observer {
                    hidProgress()
                })
                it.networkError.observe(this, Observer {
                    hidProgress()
                    showNoConnectionDialog()
                })
                it.serverError.observe(this, Observer {
                    hidProgress()
                    showServerDownDialog()
                })
                it.authorizationError.observe(this, Observer {
                    hidProgress()
                    // show Login Screen
                })
    * */
    private fun observeBaseViewModel() {
        lifecycleScope.launch(IO) {
            baseViewModel?.networkStates?.collect { states ->
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
                    else -> {}
                }
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