package com.ea.emiratesauction.core.deviceData.providers

import com.ea.emiratesauction.core.deviceData.manager.PersistenceType
import java.util.*

interface DataPersistenceProvider {
    fun save(key: String, obj: Any)
    fun get(key: String):Any
    fun update(key: String, obj: Any)
    fun delete(key: String)
    fun clear()
}