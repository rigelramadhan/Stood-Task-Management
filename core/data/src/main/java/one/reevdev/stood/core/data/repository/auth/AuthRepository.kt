package one.reevdev.stood.core.data.repository.auth

import kotlinx.coroutines.flow.Flow
import one.reevdev.stood.core.data.datasource.remote.auth.AuthRemoteDataSource
import one.reevdev.stood.core.data.datasource.remote.auth.model.LoginResponse
import one.reevdev.stood.core.data.datasource.remote.auth.model.RegisterResponse
import one.reevdev.stood.core.data.datasource.remote.auth.param.LoginParam
import one.reevdev.stood.core.data.datasource.remote.auth.param.RegisterParam
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class AuthRepository @Inject constructor(
    private val authRemoteDataSource: AuthRemoteDataSource
) : IAuthRepository {
    override fun login(loginParams: LoginParam): Flow<LoginResponse> {
        return authRemoteDataSource.login(loginParams)
    }

    override fun register(registerParams: RegisterParam): Flow<RegisterResponse> {
        return authRemoteDataSource.register(registerParams)
    }

}