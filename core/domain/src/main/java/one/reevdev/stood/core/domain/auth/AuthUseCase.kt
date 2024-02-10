package one.reevdev.stood.core.domain.auth

import kotlinx.coroutines.flow.Flow
import one.reevdev.stood.core.domain.auth.model.LoginResult
import one.reevdev.stood.core.domain.auth.model.RegisterResult
import one.reevdev.stood.core.domain.auth.params.LoginParams
import one.reevdev.stood.core.domain.auth.params.RegisterParams

interface AuthUseCase {

    fun login(loginParams: LoginParams): Flow<LoginResult>

    fun register(registerParams: RegisterParams): Flow<RegisterResult>
}