package com.ea.emiratesauction.domain.usecases

import kotlinx.coroutines.flow.Flow


abstract class UseCase<in Params,out Type> {
    abstract  fun execute(params:Params): Flow<Type>
}