package com.ea.emiratesauction.core.deviceData.providers

import android.util.Log
import javax.inject.Inject

class SecureSharedPreferencesProvider @Inject constructor() : DataPersistenceProvider {
    override fun save(key: String, obj: Any) {
        Log.d("SecureSharedPreferences", "save: ")
    }

    override fun get(key: String): Any {
        Log.d("SecureSharedPreferences", "get: ")
        return  ""
    }

    override fun update(key: String, obj: Any) {
        Log.d("SecureSharedPreferences", "update: ")
    }

    override fun delete(key: String) {
        Log.d("SecureSharedPreferences", "delete: ")
    }

    override fun clear() {
        Log.d("SecureSharedPreferences", "clear: ")
    }
}