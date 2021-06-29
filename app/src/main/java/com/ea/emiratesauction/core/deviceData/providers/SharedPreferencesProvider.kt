package com.ea.emiratesauction.core.deviceData.providers

import android.content.SharedPreferences
import com.ea.emiratesauction.common.utils.BusinessConstants
import com.google.gson.Gson
import javax.inject.Inject


/**
 * haredPreferencesProvider which use the normal sharedPrefs
 * */
class SharedPreferencesProvider @Inject constructor(
    private val sharedPreferences: SharedPreferences
) : DataPersistenceProvider {

    override fun save(key: String, obj: Any) {
        with(sharedPreferences.edit()) {
            putString(key, Gson().toJson(obj))
            apply()
        }
    }

    override suspend fun get(key: String): Any? {
        return with(sharedPreferences) {
            this.all[key]
        }
    }

    override fun update(key: String, obj: Any) {
        with(sharedPreferences.edit()) {
            putString(key, Gson().toJson(obj))
            apply()
        }
    }

    override fun delete(key: String) {
        with(sharedPreferences.edit()) {
            remove(key)
            apply()
        }
    }

    override fun clear() {
        with(sharedPreferences.edit()) {
            remove(BusinessConstants.EA_SHARED_PREFERENCE)
            apply()
        }
    }

}