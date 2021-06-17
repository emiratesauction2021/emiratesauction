package com.ea.emiratesauction.core.common.base.ui

import android.net.Uri
import android.os.Bundle
import android.os.PersistableBundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.lifecycle.lifecycleScope
import com.ea.emiratesauction.R
import com.ea.emiratesauction.core.common.di.MainApplication
import com.ea.emiratesauction.core.constants.loadingIndicators.LoadingIndicatorsTypes
import com.ea.emiratesauction.core.constants.network.errors.NetworkErrors
import com.ea.emiratesauction.features.test_toBeDeleted.network.ui.PupularPeopleListFragment
import kotlinx.coroutines.Dispatchers.IO
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch
import java.util.*

/** BaseActivity it will define the common behaviour in all activities**/
abstract class BaseActivity : AppCompatActivity(), BaseRetryActionCallback {
    /**
     * Should override to bind the layout to activity
     * To avoid doing extra stuff on different resources
     */
    abstract fun layoutId(): Int

    /**
     * @param baseViewModel is a private non-defined instance from BaseViewModel
     * Every activity has to call setViewModel fun to pass owned viewModel to define the BaseViewModel
     */
    private lateinit var baseViewModel: BaseViewModel
    fun setViewModel(viewModel: BaseViewModel) {
        baseViewModel = viewModel
    }

    /**
     * @param progressDialog an instance from Common Loading dialog
     */
//    lateinit var progressDialog: LoadingDialog
    /**
     * @param noConnectionDialog an instance from Common Loading No Connection Dialog
     */
//    lateinit var noConnectionDialog: NoConnectionDialog
    /**
     * @param serverDownDialog an instance from Common Server Error Dialog
     */
//    lateinit var serverDownDialog: ServerDownDialog


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(layoutId())
        loadResourceConfiguration()
        replaceFragment(PupularPeopleListFragment())

        val action: String? = intent?.action
        val data: Uri? = intent?.data

        (this.application as MainApplication).deepLinkManager.setDeeplinkUrl(data)
    }

    private fun replaceFragment(fragment: Fragment) {
        supportFragmentManager.beginTransaction()
            .replace(R.id.fragmentContainer, fragment)
            .commit()
    }

    /**
     * To Define , Show Loading dialog by define dialog type
     */
    fun showProgress(loadingType: LoadingIndicatorsTypes) {
        when (loadingType) {
            LoadingIndicatorsTypes.BLOCKED_SCREEN -> {
                Log.d("BaseActivity", "showProgress: ")
                //if (!this::progressDialog.isInitialized)
                //progressDialog = LoadingDialog(this);
                //progressDialog.showDialog()
            }
            LoadingIndicatorsTypes.PROGRESS_BAR -> {
                Log.d("BaseActivity", "showProgress: ")
                //if (!this::progressDialog.isInitialized)
                //progressDialog = LoadingDialog(this);
                //progressDialog.showDialog()
            }
        }
    }

    /**
     * To Hide Shown Loading dialog by define dialog type
     */
    fun hidProgress(loadingType: LoadingIndicatorsTypes) {
        when (loadingType) {
            LoadingIndicatorsTypes.BLOCKED_SCREEN -> {
                Log.d("BaseActivity", "hideProgress: ")
                //if (!this::progressDialog.isInitialized)
                //progressDialog = LoadingDialog(this);
                //progressDialog.showDialog()
            }
            LoadingIndicatorsTypes.PROGRESS_BAR -> {
                Log.d("BaseActivity", "hideProgress: ")
                //if (!this::progressDialog.isInitialized)
                //progressDialog = LoadingDialog(this);
                //progressDialog.showDialog()
            }
        }
    }

    /**
     * To Define , Show No Connection dialog
     */
    fun showNoConnectionDialog() {
        Log.d("BaseActivity", "showNoConnectionDialog: ")
//        if (!this::noConnectionDialog.isInitialized)
//            noConnectionDialog = NoConnectionDialog(this, this);
//        noConnectionDialog.showDialog()
    }

    /**
     * To Hide Shown No Connection dialog
     */
    fun hideNoConnectionDialog() {
        Log.d("BaseActivity", "hideNoConnectionDialog: ")
//        if(this::noConnectionDialog.isInitialized)
//            noConnectionDialog.hideDialog()
    }

    /**
     * To Define , Show Server Error dialog
     */
    fun showServerErrorDialog() {
        Log.d("BaseActivity", "showServerDownDialog: ")
//        if (!this::serverDownDialog.isInitialized)
//            serverDownDialog = ServerDownDialog(this, this);
//        serverDownDialog.showDialog()
    }

    /**
     * To Hide Shown Server Error dialog
     */
    fun hideServerErrorDialog() {
        Log.d("BaseActivity", "hideServerDownDialog: ")
        //  serverDownDialog.hideDialog()
    }


    override fun onResume() {
        super.onResume()
        observeBaseViewModel()
    }

    /**
     * Function that launch observation on Base viewModel in background thread
     * Handle loading states and common network errors
     */
    private fun observeBaseViewModel() {
        lifecycleScope.launch(IO) {
            baseViewModel?.let {
                manageLoadingStates(it)
                manageNetworkErrorsStates(it)
            }
        }
    }

    /**
     * Function that handle all common network states
     * Handle common changes on UI like : showConnectionDialog etc..
     */
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

    /**
     * Function that handle all loading states with type of dialog
     * Can be override in any child to handle it's own actions
     */
    open suspend fun manageLoadingStates(it: BaseViewModel) {
        it.showLoading.collect { bool ->
            when (bool) {
                true -> showProgress(LoadingIndicatorsTypes.PROGRESS_BAR)
                false -> hidProgress(LoadingIndicatorsTypes.PROGRESS_BAR)
            }
        }
    }

    /**
     * Function that handle resources configuration Like ChangeLanguage
     */
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