package one.reevdev.stood.core.data.datasource.local.auth

import android.content.Context
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.stringPreferencesKey
import androidx.datastore.preferences.preferencesDataStore
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.launch
import one.reevdev.cosmoe.utils.Logger
import one.reevdev.cosmoe.utils.emptyString
import one.reevdev.stood.core.data.utils.constants.DataStoreConstants
import one.reevdev.stood.core.data.utils.decrypt
import one.reevdev.stood.core.data.utils.emptyByteArray
import one.reevdev.stood.core.data.utils.encrypt
import javax.inject.Inject
import javax.inject.Singleton


val Context.authDataStore: DataStore<Preferences> by preferencesDataStore(name = "auth")

@Singleton
class AuthDataStore @Inject constructor(
    context: Context,
) {
    private val dataStore = context.authDataStore
    private var encryptedToken = emptyByteArray()
    private val keyToken = stringPreferencesKey(DataStoreConstants.TOKEN_KEY)

    init {
        CoroutineScope(Dispatchers.IO).launch {
            getToken().collectLatest { token ->
                setEncryptedToken(token)
            }
        }
    }

    suspend fun saveToken(token: String) {
        encryptedToken = token.encrypt()
        dataStore.edit { auth ->
            setEncryptedToken(token)
            auth[keyToken] = token
        }
    }

    suspend fun deleteToken() {
        encryptedToken = emptyByteArray()
        dataStore.edit { auth ->
            auth[keyToken] = emptyString()
        }
    }

    fun getToken(): Flow<String> {
        return dataStore.data.map { preference ->
            val token = preference[keyToken].orEmpty()
            setEncryptedToken(token)
            token
        }
    }

    fun getTokenImmediately(): String {
        Logger.debug { encryptedToken.decrypt() }
        return encryptedToken.decrypt()
    }

    private fun setEncryptedToken(token: String) {
        encryptedToken = if (token.isEmpty()) emptyByteArray() else token.encrypt()
    }
}