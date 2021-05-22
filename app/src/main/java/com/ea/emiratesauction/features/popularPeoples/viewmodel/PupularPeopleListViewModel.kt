package com.ea.emiratesauction.features.popularPeoples.viewmodel

import android.util.Log
import androidx.hilt.lifecycle.ViewModelInject
import com.ea.emiratesauction.common.base.ui.BaseViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.flowOn
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import androidx.lifecycle.viewModelScope
import com.ea.emiratesauction.core.network.result.RequestResult
import com.ea.emiratesauction.features.popularPeoples.requestTarget.PopularPeoplesRequestTarget
import com.ea.emiratesauction.features.popularPeoples.domain.usecase.GetPopularPeopleListUseCase


class PupularPeopleListViewModel @ViewModelInject constructor(private val getPopularPeopleListUseCase: GetPopularPeopleListUseCase) :
        BaseViewModel() {
    val PupTAG = "Pupular"

    fun getPopularPeopleList(testVal:String) {
        val requestParams = hashMapOf<String, Any>()
//        requestParams["api_key"] = ApiEndPoints.API_KEY
//        requestParams["language"] = AppConstants.LANGUAGE_US
//        requestParams["page"] = 1

        requestParams["test"] = testVal


        getPopularPeopleListUseCase.execute(
                PopularPeoplesRequestTarget().apply {
                    this.requestBodyParams = requestParams
                }
        )
                .flowOn(Dispatchers.IO)
                .onEach {

                    when (it) {
                        is RequestResult.Success -> {
                            val n = it.value.list
                            Log.e(PupTAG,"Success Response with return data of list ${n!!.size}")

                        }

                        is RequestResult.Fail -> {
                            val error = ValidateError(it)
                            if (error != null) {
                                Log.e(PupTAG,"Internal Fail Response with return data errorz = ${it.error!!.errorz} and " +
                                        "errorzcode = ${it.error!!.errorzcode} ")
                            }else{
                                Log.e(PupTAG,"External Fail error with error")
                            }
                        }
                    }


                }.launchIn(viewModelScope)

    }
}