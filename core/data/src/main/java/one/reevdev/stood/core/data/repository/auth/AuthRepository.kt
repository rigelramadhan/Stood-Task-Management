package one.reevdev.stood.core.data.repository.auth

import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.map
import one.reevdev.stood.core.data.datasource.local.auth.AuthDataStore
import one.reevdev.stood.core.data.datasource.remote.auth.AuthRemoteDataSource
import one.reevdev.stood.core.data.datasource.remote.auth.model.LoginResponse
import one.reevdev.stood.core.data.datasource.remote.auth.model.RegisterResponse
import one.reevdev.stood.core.data.datasource.remote.auth.param.LoginParam
import one.reevdev.stood.core.data.datasource.remote.auth.param.RegisterParam
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class AuthRepository @Inject constructor(
    private val authRemoteDataSource: AuthRemoteDataSource,
    private val authDataStore: AuthDataStore,
) : IAuthRepository {
    override fun login(loginParams: LoginParam): Flow<LoginResponse> {
        return authRemoteDataSource.login(loginParams).map {
            authDataStore.saveToken(it.data?.accessToken.orEmpty())
            it
        }
    }

    override fun register(registerParams: RegisterParam): Flow<RegisterResponse> {
        return authRemoteDataSource.register(registerParams)
    }

    override fun logout(): Flow<Boolean> = flow {
        authDataStore.deleteToken()
    }
}