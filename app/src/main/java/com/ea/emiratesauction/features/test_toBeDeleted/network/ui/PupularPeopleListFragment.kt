package com.ea.emiratesauction.features.test_toBeDeleted.network.ui

import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.core.view.isVisible
import androidx.fragment.app.activityViewModels
import com.ea.emiratesauction.R
import com.ea.emiratesauction.core.common.base.ui.BaseFragment
import com.ea.emiratesauction.core.common.base.ui.BaseViewModel
import com.ea.emiratesauction.core.constants.loadingIndicators.LoadingIndicatorsTypes
import com.ea.emiratesauction.core.crashlytics.manager.CrashesReportingManagerImp
import com.ea.emiratesauction.features.test_toBeDeleted.network.viewmodel.PupularPeopleListViewModel
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.android.synthetic.main.fragment_pupular_people_list.*
import javax.inject.Inject

@AndroidEntryPoint
class PupularPeopleListFragment : BaseFragment() {

    private val viewModel: PupularPeopleListViewModel by activityViewModels()
    override fun layoutId(): Int {
        return R.layout.fragment_pupular_people_list
    }

    @Inject lateinit var crashesReportingManagerImp :CrashesReportingManagerImp

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setViewModel(viewModel)

    }

    override fun onRetry() {
        //TODO("Not yet implemented")
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        TestSucBtn.setOnClickListener {
            viewModel.getPopularPeopleList("asdasd")
        }

        TestfailBtn.setOnClickListener {
            viewModel.getPopularPeopleList("fail")
        }

    }

}