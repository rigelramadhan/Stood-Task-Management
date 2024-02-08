package one.reevdev.stood.core.domain.auth

import one.reevdev.stood.core.data.datasource.remote.auth.model.LoginResponse
import one.reevdev.stood.core.data.datasource.remote.auth.model.RegisterResponse
import one.reevdev.stood.core.data.datasource.remote.auth.param.LoginParam
import one.reevdev.stood.core.data.datasource.remote.auth.param.RegisterParam
import one.reevdev.stood.core.domain.auth.model.LoginResult
import one.reevdev.stood.core.domain.auth.model.RegisterResult
import one.reevdev.stood.core.domain.auth.params.LoginParams
import one.reevdev.stood.core.domain.auth.params.RegisterParams

fun LoginResponse.toDomain() = LoginResult(
    displayName = data?.displayName.orEmpty(),
    accessToken = data?.accessToken.orEmpty(),
)

fun RegisterResponse.toDomain() = RegisterResult(
    displayName = data?.displayName.orEmpty()
)

fun LoginParams.toRequest() = LoginParam(
    password = password,
    email = email
)

fun RegisterParams.toRequest() = RegisterParam(
    password = password,
    displayName = displayName,
    roleId = roleId,
    confirmPassword = confirmPassword,
    email = email,
    username = username
)