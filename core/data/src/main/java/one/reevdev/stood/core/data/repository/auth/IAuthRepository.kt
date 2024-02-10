package one.reevdev.stood.core.data.repository.auth

import kotlinx.coroutines.flow.Flow
import one.reevdev.stood.core.data.datasource.remote.auth.model.LoginResponse
import one.reevdev.stood.core.data.datasource.remote.auth.model.RegisterResponse
import one.reevdev.stood.core.data.datasource.remote.auth.param.LoginParam
import one.reevdev.stood.core.data.datasource.remote.auth.param.RegisterParam

interface IAuthRepository {

    fun login(loginParams: LoginParam): Flow<LoginResponse>

    fun register(registerParams: RegisterParam): Flow<RegisterResponse>

    fun logout(): Flow<Boolean>
}