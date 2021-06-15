package com.ea.emiratesauction.core.common.base.ui

import android.os.Bundle
import android.os.LocaleList
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import java.util.*


abstract class BaseFragment : Fragment(), BaseRetryActionCallback {
    abstract fun layoutId(): Int
    abstract fun subscribeObservers()

//    abstract var baseViewModel : MainAppViewModel?
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
        loadResourceAndroid()
    }


    open fun onBackPressed() {}

    fun showProgress() {
//        if (!this::progessDialog.isInitialized)
//            progessDialog = LoadingDialog(this);
//        progessDialog.showDialog()
    }

    fun hidProgress() {
//        if(this::progessDialog.isInitialized){
//            progessDialog.hideDialog()
//        }
    }


    fun showNoConnectionDialog() {
//        if (!this::noConnectionDialog.isInitialized)
//            noConnectionDialog = NoConnectionDialog(this, this);
//        noConnectionDialog.showDialog()
    }


    fun hideNoConnectionDialog() {
//        if(this::noConnectionDialog.isInitialized)
//            noConnectionDialog.hideDialog()
    }

    fun showServerDownDialog() {
//        if (!this::serverDownDialog.isInitialized)
//            serverDownDialog = ServerDownDialog(this, this);
//        serverDownDialog.showDialog()
    }


    fun hideServerDownDialog() {
        //  serverDownDialog.hideDialog()
    }


    override fun onResume() {
        super.onResume()
        observeBaseViewModel()
    }

    private fun observeBaseViewModel() {
//        baseViewModel?.let {
//            it.successMessage.observe(this, Observer {
//                hidProgress()
//            })
//            it.errorMessage.observe(this, Observer {
//                hidProgress()
//            })
//            it.networkError.observe(this, Observer {
//                hidProgress()
//                showNoConnectionDialog()
//            })
//            it.serverError.observe(this, Observer {
//                hidProgress()
//                showServerDownDialog()
//            })
//            it.authorizationError.observe(this, Observer {
//                hidProgress()
//                // show Login Screen
//            })
//        }
    }

    @Suppress("DEPRECATION")
    fun loadResourceAndroid() {
        val dm = resources.displayMetrics
        val conf = resources.configuration
        val lang = "EN" // will be Replaced by App Language
        conf.setLocale(Locale(lang))
        resources.updateConfiguration(conf, dm)
    }
}