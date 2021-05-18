package com.ea.emiratesauction.common.base.domain

import kotlinx.coroutines.flow.Flow


abstract class BaseUseCase<in Params,out Type> {
    abstract  fun execute(params:Params): Flow<Type>
}