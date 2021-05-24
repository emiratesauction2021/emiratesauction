package com.ea.emiratesauction.features.popularPeoples.viewmodel

import android.util.Log
import androidx.datastore.preferences.protobuf.Api
import androidx.hilt.lifecycle.ViewModelInject
import com.ea.emiratesauction.common.base.ui.BaseViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.flowOn
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import androidx.lifecycle.viewModelScope
import com.ea.emiratesauction.common.utils.ApiEndPoints
import com.ea.emiratesauction.core.constants.network.NetworkRequestParametersType
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
                    this.parameters = NetworkRequestParametersType.Standard(requestParams)
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
                            val error = validateError(it)
                            if (error != null) {
                                Log.e(PupTAG,"Internal Fail Response with return data errorz = ${it.networkError.error!!.errorz} and " +
                                        "errorzcode = ${it.networkError.error!!.errorzcode} ")
                            }else{
                                Log.e(PupTAG,"External Fail error with error")
                            }
                        }
                    }


                }.launchIn(viewModelScope)

    }
}