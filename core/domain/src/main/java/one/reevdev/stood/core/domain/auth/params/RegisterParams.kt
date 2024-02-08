package one.reevdev.stood.core.domain.auth.params

data class RegisterParams(
	val password: String,
	val displayName: String,
	val roleId: String,
	val confirmPassword: String,
	val email: String,
	val username: String
)