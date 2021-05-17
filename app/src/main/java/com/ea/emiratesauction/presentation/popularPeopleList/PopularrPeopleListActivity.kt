package com.ea.emiratesauction.presentation.popularPeopleList


import com.ea.emiratesauction.common.baseUi.BaseActivity
import com.ea.emiratesauction.common.baseUi.BaseFragment
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class PopularrPeopleListActivity : BaseActivity() {

    override fun fragment(): BaseFragment {
        return PupularPeopleListFragment()

    }
}