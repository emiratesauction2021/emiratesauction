package com.ea.emiratesauction.viewmodels

import android.util.Log
import androidx.fragment.app.activityViewModels
import com.ea.emiratesauction.BaseHiltTest
import com.ea.emiratesauction.core.constants.network.parameters.NetworkRequestParametersType
import com.ea.emiratesauction.core.logger.LogType
import com.ea.emiratesauction.core.logger.LoggingManager
import com.ea.emiratesauction.core.logger.log
import com.ea.emiratesauction.core.network.result.RequestResult
import com.ea.emiratesauction.features.test_toBeDeleted.network.domain.usecase.GetPopularPeopleListUseCase
import com.ea.emiratesauction.features.test_toBeDeleted.network.requestTarget.PopularPeoplesRequestTarget
import com.ea.emiratesauction.features.test_toBeDeleted.network.viewmodel.PupularPeopleListViewModel
import dagger.hilt.android.testing.HiltAndroidTest
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.runBlocking
import org.junit.Test
import java.util.logging.Logger
import javax.inject.Inject


@HiltAndroidTest
class TestViewModelOne : BaseHiltTest() {
    @Inject lateinit var useCase: GetPopularPeopleListUseCase
    var viewModel: PupularPeopleListViewModel? = null

    @Test
    fun testViewModel() {
        viewModel = PupularPeopleListViewModel(useCase)
        viewModel?.getPopularPeopleList("Hello")
    }

    @Test
    fun testUseCase() = runBlocking {
        val map = mutableMapOf<String, String>()
        map["test"] = "asasasa"
        useCase.execute(PopularPeoplesRequestTarget().apply {
            this.parameters = NetworkRequestParametersType.Standard(
                map
            )
        }).collect {
            when (it) {
                is RequestResult.Success -> {
                    log.debug(tag = "testUserCase0", message = it.value)
                }

                is RequestResult.Fail -> {
                    log.debug(tag = "testUserCase1", message = it.networkError)
                }
            }

        }
    }
}