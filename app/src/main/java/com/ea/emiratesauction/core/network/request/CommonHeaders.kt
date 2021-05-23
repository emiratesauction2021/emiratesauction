package com.ea.emiratesauction.core.network.request

interface CommonHeadersInterface {
    fun aaa() : Map<String, Any>
}

class CommonHeaders: CommonHeadersInterface{
    override fun aaa(): Map<String, Any> {
        print("There !!")
        return hashMapOf()
    }

}

fun CommonHeaders.aaa(): Map<String, Any> {
    print("Here !!")
    return hashMapOf()
}