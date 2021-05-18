package com.ea.emiratesauction.features.popularPeoples.ui


import com.ea.emiratesauction.common.base.ui.BaseActivity
import com.ea.emiratesauction.common.base.ui.BaseFragment
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class PopularPeopleListActivity : BaseActivity() {

    override fun fragment(): BaseFragment {
        return PupularPeopleListFragment()

    }
}