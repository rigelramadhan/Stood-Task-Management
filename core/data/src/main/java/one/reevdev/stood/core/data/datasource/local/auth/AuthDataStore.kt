package one.reevdev.stood.core.data.datasource.local.auth

import android.content.Context
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.stringPreferencesKey
import androidx.datastore.preferences.preferencesDataStore
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import one.reevdev.cosmoe.utils.emptyString
import javax.inject.Inject
import javax.inject.Singleton


val Context.authDataStore: DataStore<Preferences> by preferencesDataStore(name = "auth")

@Singleton
class AuthDataStore @Inject constructor(
    context: Context,
) {
    private val dataStore = context.authDataStore

    private val keyToken = stringPreferencesKey(KEY_TOKEN)

    suspend fun saveToken(token: String) {
        dataStore.edit { auth ->
            auth[keyToken] = token
        }
    }

    suspend fun deleteToken() {
        dataStore.edit { auth ->
            auth[keyToken] = emptyString()
        }
    }

    fun getToken(): Flow<String> {
        return dataStore.data.map { preference ->
            preference[keyToken].orEmpty()
        }
    }

    companion object {
        private const val KEY_TOKEN = "key_token"
    }
}