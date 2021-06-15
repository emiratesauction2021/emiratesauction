package com.ea.emiratesauction.core.common.base.ui

import android.content.Context
import android.os.Bundle
import android.os.PersistableBundle
import androidx.appcompat.app.AppCompatActivity
import com.ea.emiratesauction.R
import java.util.*

abstract class BaseActivity : AppCompatActivity(), BaseRetryActionCallback {


//    abstract var baseViewModel : MainAppViewModel?
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


    override fun onCreate(savedInstanceState: Bundle?, persistentState: PersistableBundle?) {
        super.onCreate(savedInstanceState, persistentState)
        loadResourceAndroid()
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