package com.ea.emiratesauction.core.deviceData.manager

import com.ea.emiratesauction.core.deviceData.providers.SecureSharedPreferencesProvider
import com.ea.emiratesauction.core.deviceData.providers.SharedPreferencesProvider
import com.google.gson.Gson
import javax.inject.Inject


/**
 * DevicePersistenceManager to mange secure and in secure data on the device
 * @param normalDataPersistence it is normal shared preferences
 * @param secureDataPersistence it is jetpack dataStore
 * */
class DevicePersistenceManager @Inject constructor(
    val normalDataPersistence: SharedPreferencesProvider,
    val secureDataPersistence: SecureSharedPreferencesProvider
) : DevicePersistenceManagerInterface {

    /**
     * @param key to save the value with a specific name
     * @param obj the actual data that will be saved
     * @param type enum to get the way to save secure or normal
     * */
    override fun save(key: String, obj: Any, type: PersistenceType) {
        when (type) {
            PersistenceType.NORMAL -> {
                normalDataPersistence.save(key, obj)
            }
            PersistenceType.SECURE -> {
                secureDataPersistence.save(key, obj)
            }
        }
    }

    /**
     * @param key to get the value with a specific name
     * @param type enum to get the way to save secure or normal
     * <T> pass the class Name when you get the data {
     *  this.get<String or Any class>
     * }
     * */
    suspend inline fun <reified T : Any> get(key: String, type: PersistenceType): T? {
        return when (type) {
            PersistenceType.NORMAL -> {
                val data = normalDataPersistence.get(key).toString()
                ConvertType<T>()
                    .convert(data, T::class.java)
            }
            PersistenceType.SECURE -> {
                val data = secureDataPersistence.get(key).toString()
                ConvertType<T>()
                    .convert(data, T::class.java)
            }
        }
    }

    /**
     * @param key to update the value with a specific name
     * @param obj the actual data that will be saved
     * @param type enum to get the way to save secure or normal
     * */
    override fun update(key: String, obj: Any, type: PersistenceType) {
        save(key, obj, type)
    }

    /**
     * @param key to delete the value with a specific name
     * @param type enum to get the way to save secure or normal
     * */
    override fun delete(key: String, type: PersistenceType) {
        when (type) {
            PersistenceType.NORMAL -> {
                normalDataPersistence.delete(key)
            }
            PersistenceType.SECURE -> {
                secureDataPersistence.delete(key)
            }
        }
    }

    /**
     * Clear all data on the device
     * @param type enum to get the way to save secure or normal
     * */
    override fun clear(type: PersistenceType) {
        when (type) {
            PersistenceType.NORMAL -> {
                normalDataPersistence.clear()
            }
            PersistenceType.SECURE -> {
                secureDataPersistence.clear()
            }
        }
    }

    /**
     * ConvertType<T> to convert the saved data from json string to a normal object
     * */
    inner class ConvertType<T> {
        fun convert(data: String, type: Class<T>): T? {
            return Gson().fromJson(data, type)
        }
    }
}