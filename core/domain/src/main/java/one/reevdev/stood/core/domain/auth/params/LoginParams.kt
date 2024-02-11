package one.reevdev.stood.core.domain.auth.params

import one.reevdev.cosmoe.utils.emptyString

data class LoginParams(
    val email: String = emptyString(),
    val password: String = emptyString(),
)
