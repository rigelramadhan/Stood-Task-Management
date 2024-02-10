package one.reevdev.stood.core.domain.task

import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import one.reevdev.stood.core.data.repository.auth.IAuthRepository
import one.reevdev.stood.core.domain.auth.AuthUseCase
import one.reevdev.stood.core.domain.auth.model.LoginResult
import one.reevdev.stood.core.domain.auth.model.RegisterResult
import one.reevdev.stood.core.domain.auth.params.LoginParams
import one.reevdev.stood.core.domain.auth.params.RegisterParams
import one.reevdev.stood.core.domain.auth.toDomain
import one.reevdev.stood.core.domain.auth.toRequest
import javax.inject.Inject

class AuthInteractor @Inject constructor(
    private val authRepository: IAuthRepository
) : AuthUseCase {
    override fun login(loginParams: LoginParams): Flow<LoginResult> {
        return authRepository.login(loginParams.toRequest()).map { it.toDomain() }
    }

    override fun register(registerParams: RegisterParams): Flow<RegisterResult> {
        return authRepository.register(registerParams.toRequest()).map { it.toDomain() }
    }
}