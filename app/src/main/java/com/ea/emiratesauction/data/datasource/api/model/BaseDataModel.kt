package com.ea.emiratesauction.data.datasource.api.model

data class BaseDataModel<T>(
        val status:Boolean,
        val code:Int,
        val message:String,
        val data:T?
)