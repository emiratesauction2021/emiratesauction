package com.ea.emiratesauction.features.popularPeoples.viewmodel

import android.util.Log
import androidx.hilt.lifecycle.ViewModelInject
import com.ea.emiratesauction.common.base.ui.BaseViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.flowOn
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import androidx.lifecycle.viewModelScope
import com.ea.emiratesauction.network_layer.ResultWrapper
import com.ea.emiratesauction.common.utils.AppConstants
import com.ea.emiratesauction.features.popularPeoples.requestTarget.PopularPeoplesRequestTarget
import com.ea.emiratesauction.common.utils.ApiEndPoints
import com.ea.emiratesauction.features.popularPeoples.domain.usecase.GetPopularPeopleListUseCase


class PupularPeopleListViewModel @ViewModelInject constructor(private val getPopularPeopleListUseCase: GetPopularPeopleListUseCase) :
        BaseViewModel() {

    fun getPopularPeopleList() {
        val requestParams = hashMapOf<String, Any>()
        requestParams["api_key"] = ApiEndPoints.API_KEY
        requestParams["language"] = AppConstants.LANGUAGE_US
        requestParams["page"] = 1



        getPopularPeopleListUseCase.execute(
                PopularPeoplesRequestTarget().apply {
                    this.requestQueryParams = requestParams
                }
        )
                .flowOn(Dispatchers.IO)
                .onEach {

                    when (it) {
                        is ResultWrapper.Success -> {
                            Log.e("modelResponse", it.value.toString())
                            Log.v("coroutine viewModel", Thread.currentThread().name)
                        }

                        is ResultWrapper.Fail -> {
                            val error = ValidateError(it)
                            if (error != null) {
                                // handle internal error
                            }
                        }
                    }


                }.launchIn(viewModelScope)

    }
}