package one.reevdev.stood.core.data.datasource.local.datastore

import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.booleanPreferencesKey
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.stringPreferencesKey
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import one.reevdev.cosmoe.utils.emptyString
import one.reevdev.stood.core.data.utils.constants.DataStoreConstants

class AuthPreferences(private val dataStore: DataStore<Preferences>) {

    private val tokenKey = stringPreferencesKey(DataStoreConstants.TOKEN_KEY)
    private val firstTimeKey = booleanPreferencesKey(DataStoreConstants.FIRST_TIME_KEY)

    fun getToken(): Flow<String> {
        return dataStore.data.map {
            it[tokenKey] ?: emptyString()
        }
    }

    suspend fun setToken(token: String) {
        dataStore.edit {
            it[tokenKey] = token
        }
    }

    fun isFirstTime(): Flow<Boolean> {
        return dataStore.data.map {
            it[firstTimeKey] ?: true
        }
    }

    suspend fun setFirstTime(isFirstTime: Boolean) {
        dataStore.edit {
            it[firstTimeKey] = isFirstTime
        }
    }

    suspend fun deleteToken() {
        dataStore.edit {
            it[tokenKey] = emptyString()
        }
    }
}