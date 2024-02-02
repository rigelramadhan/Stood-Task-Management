package one.reevdev.stood.core.domain.auth.model

import one.reevdev.cosmoe.utils.emptyString

data class RegisterResult(
    val displayName: String = emptyString(),
)
