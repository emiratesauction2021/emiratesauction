package com.ea.emiratesauction.features.test_toBeDeleted.network.ui


import android.os.Bundle
import androidx.activity.viewModels
import androidx.fragment.app.activityViewModels
import com.ea.emiratesauction.R
import com.ea.emiratesauction.core.common.base.ui.BaseActivity
import com.ea.emiratesauction.core.common.base.ui.BaseFragment
import com.ea.emiratesauction.core.common.base.ui.BaseViewModel
import com.ea.emiratesauction.features.test_toBeDeleted.network.viewmodel.PupularPeopleListViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class PopularPeopleListActivity : BaseActivity() {
    private val viewModel: PupularPeopleListViewModel by viewModels()
    override fun layoutId(): Int {
        return R.layout.activity_base
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setViewModel(viewModel)
    }

    override fun onRetry() {
        TODO("Not yet implemented")
    }

}