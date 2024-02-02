package one.reevdev.stood.core.domain.auth

import one.reevdev.stood.core.data.datasource.remote.auth.model.LoginResponse
import one.reevdev.stood.core.data.datasource.remote.auth.model.RegisterResponse
import one.reevdev.stood.core.domain.auth.model.LoginResult
import one.reevdev.stood.core.domain.auth.model.RegisterResult

fun LoginResponse.toDomain() = LoginResult(
    displayName = data?.displayName.orEmpty(),
    accessToken = data?.accessToken.orEmpty(),
)

fun RegisterResponse.toDomain() = RegisterResult(
    displayName = data?.displayName.orEmpty()
)