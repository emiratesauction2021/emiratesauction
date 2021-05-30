package com.ea.emiratesauction.core.utilities.network

object NetworkExceptions {

    class EncodingMethodException(message:String):IllegalArgumentException()
    class CompositeMethodException(message:String):IllegalArgumentException()
}