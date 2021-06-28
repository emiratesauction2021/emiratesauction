package com.ea.emiratesauction.features.test_toBeDeleted.network.ui


import com.ea.emiratesauction.core.common.base.ui.BaseActivity
import com.ea.emiratesauction.core.common.base.ui.BaseFragment
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class PopularPeopleListActivity : BaseActivity() {

    override fun fragment(): BaseFragment {
        return PupularPeopleListFragment()

    }
}