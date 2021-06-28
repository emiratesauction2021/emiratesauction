package com.ea.emiratesauction.core.deviceData.manager

import android.util.Log
import com.ea.emiratesauction.core.deviceData.providers.SecureSharedPreferencesProvider
import com.ea.emiratesauction.core.deviceData.providers.SharedPreferencesProvider
import javax.inject.Inject

class DevicePersistenceManager @Inject constructor(
    val normalDataPersistence: SharedPreferencesProvider,
    val secureDataPersistence: SecureSharedPreferencesProvider
) : DevicePersistenceManagerInterface {
    override fun save(key: String, obj: Any, type: PersistenceType) {
        Log.d("DevicePersistence", "save: ")

    }

    override fun get(key: String, type: PersistenceType): Any {
        Log.d("DevicePersistence", "get:")
        return ""
    }

    override fun update(key: String, obj: Any, type: PersistenceType) {
        Log.d("DevicePersistence", "update: ")
    }

    override fun delete(key: String, type: PersistenceType) {
        Log.d("DevicePersistence", "delete: ")
    }

    override fun clear() {
        Log.d("DevicePersistence", "clear:")
    }


}