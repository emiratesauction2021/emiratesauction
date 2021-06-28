package com.ea.emiratesauction.core.deviceData.manager

interface DevicePersistenceManagerInterface {
    fun save(key: String, obj: Any, type: PersistenceType)
    fun get(key: String, type: PersistenceType):Any
    fun update(key: String, obj: Any, type: PersistenceType)
    fun delete(key: String, type: PersistenceType)
    fun clear()
}