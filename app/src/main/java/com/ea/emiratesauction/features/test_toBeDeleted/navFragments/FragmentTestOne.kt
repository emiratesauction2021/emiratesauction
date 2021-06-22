package com.ea.emiratesauction.features.test_toBeDeleted.navFragments

import android.os.Bundle
import android.view.View
import androidx.activity.OnBackPressedCallback
import androidx.fragment.app.activityViewModels
import androidx.navigation.fragment.findNavController
import com.ea.emiratesauction.R
import com.ea.emiratesauction.core.common.base.ui.BaseFragment
import com.ea.emiratesauction.features.test_toBeDeleted.network.viewmodel.PupularPeopleListViewModel
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.android.synthetic.main.fragment_deep_link_test_one.*


@AndroidEntryPoint
class FragmentTestOne : BaseFragment() {
    override fun layoutId() = R.layout.fragment_deep_link_test_one

    private val viewModel: PupularPeopleListViewModel by activityViewModels()


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setViewModel(viewModel)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        tv_test.setOnClickListener {
            findNavController().navigate(R.id.fragmentTestTwo)
        }

        arguments?.let {
            tv_test.text = it.getString("PLATES","no data")
        }

    }

    override fun onRetry() {
    }
}