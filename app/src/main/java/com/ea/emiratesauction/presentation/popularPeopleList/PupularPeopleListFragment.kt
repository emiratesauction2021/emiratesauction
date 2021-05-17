package com.ea.emiratesauction.presentation.popularPeopleList

import android.os.Bundle
import android.view.View
import androidx.fragment.app.activityViewModels
import com.ea.emiratesauction.R
import com.ea.emiratesauction.common.baseUi.BaseFragment
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class PupularPeopleListFragment : BaseFragment() {
    private val viewModel: PupularPeopleListViewModel by activityViewModels()

    override fun layoutId(): Int {
        return R.layout.fragment_pupular_people_list
    }


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel.getPopularPeopleList()
    }

    override fun subscribeObservers() {
    }



}