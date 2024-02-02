package one.reevdev.stood.core.domain.auth.model

import one.reevdev.cosmoe.utils.emptyString

data class LoginResult(
    val displayName: String = emptyString(),
    val accessToken: String = emptyString(),
)
