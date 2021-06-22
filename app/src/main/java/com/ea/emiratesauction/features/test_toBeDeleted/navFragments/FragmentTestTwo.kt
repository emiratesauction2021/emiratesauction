package com.ea.emiratesauction.features.test_toBeDeleted.navFragments

import android.os.Bundle
import androidx.fragment.app.activityViewModels
import androidx.navigation.fragment.findNavController
import com.ea.emiratesauction.R
import com.ea.emiratesauction.core.common.base.ui.BaseFragment
import com.ea.emiratesauction.features.test_toBeDeleted.network.viewmodel.PupularPeopleListViewModel
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.Dispatchers.Main
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch

@AndroidEntryPoint
class FragmentTestTwo : BaseFragment() {
    override fun layoutId() = R.layout.fragment_deep_link_test_two

    private val viewModel: PupularPeopleListViewModel by activityViewModels()


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setViewModel(viewModel)
    }

    override fun onRetry() {
    }
}