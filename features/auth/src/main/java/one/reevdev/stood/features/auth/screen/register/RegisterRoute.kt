package one.reevdev.stood.features.auth.screen.register

import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import one.reevdev.cosmoe.utils.emptyString
import one.reevdev.stood.core.domain.auth.params.RegisterParams

@Composable
fun RegisterRoute(
    modifier: Modifier = Modifier,
    viewModel: RegisterViewModel = hiltViewModel(),
    onLoginButtonClick: () -> Unit,
    onRegisterSuccess: () -> Unit,
) {
    val uiState by viewModel.uiState.collectAsStateWithLifecycle()

    var username by rememberSaveable { mutableStateOf(emptyString()) }
    var email by rememberSaveable { mutableStateOf(emptyString()) }
    var displayName by rememberSaveable { mutableStateOf(emptyString()) }
    var password by rememberSaveable { mutableStateOf(emptyString()) }
    var confirmPassword by rememberSaveable { mutableStateOf(emptyString()) }
    var isPasswordVisible by rememberSaveable { mutableStateOf(false) }

    LaunchedEffect(uiState.isRegisterSuccess) {
        if (uiState.isRegisterSuccess) onRegisterSuccess()
    }

    RegisterScreen(
        modifier = modifier,
        username = username,
        email = email,
        displayName = displayName,
        password = password,
        confirmPassword = confirmPassword,
        isPasswordVisible = isPasswordVisible,
        onUsernameChange = { username = it },
        onEmailChange = { email = it },
        onDisplayNameChange = { displayName = it },
        onPasswordChange = { password = it },
        onConfirmPasswordChange = { confirmPassword = it },
        onPasswordVisibilityClick = { isPasswordVisible = it },
        onRegisterButtonClick = {
            val registerParams = RegisterParams(
                username = username,
                email = email,
                displayName = displayName,
                password = password,
                confirmPassword = confirmPassword,
                roleId = emptyString()
            )
            viewModel.register(registerParams)
        },
        onLoginButtonClick = onLoginButtonClick
    )
}