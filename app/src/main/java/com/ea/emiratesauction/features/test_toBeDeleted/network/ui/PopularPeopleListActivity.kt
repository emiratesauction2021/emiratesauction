package com.ea.emiratesauction.features.test_toBeDeleted.network.ui


import androidx.activity.viewModels
import com.ea.emiratesauction.core.common.base.ui.BaseActivity
import com.ea.emiratesauction.core.common.base.ui.BaseFragment
import com.ea.emiratesauction.core.common.base.ui.BaseViewModel
import com.ea.emiratesauction.features.test_toBeDeleted.network.viewmodel.PupularPeopleListViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class PopularPeopleListActivity : BaseActivity() {
    override var baseViewModel: BaseViewModel?
        get() = null
        set(value) {}

    override fun fragment(): BaseFragment {
        return PupularPeopleListFragment()

    }

    override fun onRetry() {
        TODO("Not yet implemented")
    }
}