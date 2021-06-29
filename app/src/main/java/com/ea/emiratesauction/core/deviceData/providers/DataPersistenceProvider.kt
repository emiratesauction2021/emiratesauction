package com.ea.emiratesauction.core.deviceData.providers

/**
 * Crud Operations for the providers
 * */
interface DataPersistenceProvider {
    fun save(key: String, obj: Any)
    suspend fun get(key: String): Any?
    fun update(key: String, obj: Any)
    fun delete(key: String)
    fun clear()
}