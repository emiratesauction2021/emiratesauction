package com.ea.emiratesauction.core.deviceData.providers

import android.util.Log
import javax.inject.Inject

class SharedPreferencesProvider @Inject constructor(): DataPersistenceProvider {
    override fun save(key: String, obj: Any) {
        Log.d("SharedPreferences", "save: ")
    }

    override fun get(key: String): Any {
        Log.d("SharedPreferences", "get: ")
        return ""
    }

    override fun update(key: String, obj: Any) {
        Log.d("SharedPreferences", "update: ")
    }

    override fun delete(key: String) {
        Log.d("SharedPreferences", "delete: ")
    }

    override fun clear() {
        Log.d("SharedPreferences", "clear: ")
    }
}