package com.ea.emiratesauction.presentation.popularPeopleList

import android.util.Log
import androidx.hilt.lifecycle.ViewModelInject
import com.ea.emiratesauction.common.baseUi.BaseViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.flowOn
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import androidx.lifecycle.viewModelScope
import com.ea.emiratesauction.common.network_layer.layer_dependencies.ResultWrapper
import com.ea.emiratesauction.common.utils.AppConstants
import com.ea.emiratesauction.domain.models.PopularPeopleListReqModel
import com.ea.emiratesauction.domain.models.PopularPeopleListResponse
import com.ea.emiratesauction.domain.usecases.UseCase
import com.ea.emiratesauction.common.utils.ApiEndPoints
import com.ea.emiratesauction.domain.usecases.GetPopularPeopleListUseCase
import javax.inject.Inject


class PupularPeopleListViewModel @ViewModelInject constructor(private val getPopularPeopleListUseCase: GetPopularPeopleListUseCase) :
    BaseViewModel() {

    fun getPopularPeopleList() {
        getPopularPeopleListUseCase.execute(
            PopularPeopleListReqModel(
                ApiEndPoints.API_KEY,
            AppConstants.LANGUAGE_US,1)
        )
                .flowOn(Dispatchers.IO)
            .onEach {

                when(it){
                    is ResultWrapper.Success ->{
                        Log.v("modelResponse", it.value.toString() )
                        Log.v("coroutine viewModel", Thread.currentThread().name)

                    }
                }




            }.launchIn(viewModelScope)

    }
}