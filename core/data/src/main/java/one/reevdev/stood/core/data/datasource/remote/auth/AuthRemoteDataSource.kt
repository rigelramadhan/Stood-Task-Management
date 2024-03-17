package one.reevdev.stood.core.data.datasource.remote.auth

import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import one.reevdev.stood.core.data.datasource.remote.auth.model.LoginResponse
import one.reevdev.stood.core.data.datasource.remote.auth.model.RegisterResponse
import one.reevdev.stood.core.data.datasource.remote.auth.param.LoginParam
import one.reevdev.stood.core.data.datasource.remote.auth.param.RegisterParam
import javax.inject.Inject

class AuthRemoteDataSource @Inject constructor(
    private val apiService: AuthApiService
) {
    fun login(loginParam: LoginParam): Flow<LoginResponse> = flow {
        emit(apiService.login(loginParam))
    }

    fun register(registerParam: RegisterParam): Flow<RegisterResponse> = flow {
        emit(apiService.register(registerParam))
    }
}