package com.ea.emiratesauction.features.test_toBeDeleted.network.ui


import android.os.Bundle
import androidx.activity.viewModels
import com.ea.emiratesauction.core.common.base.ui.BaseActivity
import com.ea.emiratesauction.features.test_toBeDeleted.network.viewmodel.PupularPeopleListViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class PopularPeopleListActivity : BaseActivity() {
    private val viewModel: PupularPeopleListViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setViewModel(viewModel)
    }

    override fun onRetry() {
        TODO("Not yet implemented")
    }

}