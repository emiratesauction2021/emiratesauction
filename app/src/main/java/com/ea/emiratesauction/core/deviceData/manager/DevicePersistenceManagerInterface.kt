package com.ea.emiratesauction.core.deviceData.manager

/**
* Crud Operations for the DevicePersistenceManager
* */
interface DevicePersistenceManagerInterface {
    fun save(key: String, obj: Any, type: PersistenceType)
    fun update(key: String, obj: Any, type: PersistenceType)
    fun delete(key: String, type: PersistenceType)
    fun clear(type: PersistenceType)
}