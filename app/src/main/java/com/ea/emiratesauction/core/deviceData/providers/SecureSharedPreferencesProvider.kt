package com.ea.emiratesauction.core.deviceData.providers

import android.content.Context
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.stringPreferencesKey
import androidx.datastore.preferences.preferencesDataStore
import com.ea.emiratesauction.common.utils.BusinessConstants.Companion.SECURE_PREFERENCES_NAME
import com.google.gson.Gson
import dagger.hilt.android.qualifiers.ApplicationContext
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers.IO
import kotlinx.coroutines.Job
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.launch
import javax.inject.Inject
import javax.inject.Singleton

/**
 * SecureSharedPreferencesProvider which use the jetpack dataStore as secure data
 * */
class SecureSharedPreferencesProvider @Inject constructor(
    @ApplicationContext val context: Context
) : DataPersistenceProvider {

    /**
    * All data will be saved as Json String
    * */
    override fun save(key: String, obj: Any) {
        CoroutineScope(IO + Job()).launch {
            context.dataStore.edit { preferences ->
                preferences[stringPreferencesKey(key)] = Gson().toJson(obj)
            }
        }
    }

    override suspend fun get(key: String): Any? {
        val counterKey = stringPreferencesKey(key)
        return context.dataStore.data.map {
            it[counterKey] ?: ""
        }.first()
    }

    override fun update(key: String, obj: Any) {
        save(key, obj)
    }

    override fun delete(key: String) {
        CoroutineScope(IO + Job()).launch {
            context.dataStore.edit { preferences ->
                preferences.remove(key = stringPreferencesKey(key))
            }
        }
    }

    override fun clear() {
        CoroutineScope(IO + Job()).launch {
            context.dataStore.edit { preferences ->
                preferences.clear()
            }
        }
    }
}
private val Context.dataStore by preferencesDataStore(name = SECURE_PREFERENCES_NAME)