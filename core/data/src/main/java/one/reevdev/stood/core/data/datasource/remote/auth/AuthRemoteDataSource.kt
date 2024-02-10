package one.reevdev.stood.core.data.datasource.remote.auth

import kotlinx.coroutines.flow.Flow
import one.reevdev.stood.core.data.datasource.remote.auth.model.LoginResponse
import one.reevdev.stood.core.data.datasource.remote.auth.model.RegisterResponse
import one.reevdev.stood.core.data.datasource.remote.auth.param.LoginParam
import one.reevdev.stood.core.data.datasource.remote.auth.param.RegisterParam
import javax.inject.Inject

class AuthRemoteDataSource @Inject constructor(
    private val apiService: AuthApiService
) : AuthApiService {
    override fun login(loginParam: LoginParam): Flow<LoginResponse> {
        return apiService.login(loginParam)
    }

    override fun register(registerParam: RegisterParam): Flow<RegisterResponse> {
        return apiService.register(registerParam)
    }
}