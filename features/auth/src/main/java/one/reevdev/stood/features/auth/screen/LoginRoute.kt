package one.reevdev.stood.features.auth.screen

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
import one.reevdev.stood.core.domain.auth.params.LoginParams

@Composable
fun LoginRoute(
    modifier: Modifier = Modifier,
    viewModel: LoginViewModel = hiltViewModel(),
    onLoginSuccess: () -> Unit,
) {
    val uiState by viewModel.uiState.collectAsStateWithLifecycle()

    var username by rememberSaveable { mutableStateOf(emptyString()) }
    var password by rememberSaveable { mutableStateOf(emptyString()) }
    var isPasswordVisible by rememberSaveable { mutableStateOf(false) }

    LaunchedEffect(uiState.isLoginSuccess) {
        if (uiState.isLoginSuccess) onLoginSuccess()
    }

    LoginScreen(
        modifier = modifier,
        username = username,
        password = password,
        isPasswordVisible = isPasswordVisible,
        onUsernameChange = { username = it },
        onPasswordChange = { password = it },
        onPasswordVisibilityClick = { isPasswordVisible = it },
        onLoginButtonClick = {
            val loginParams = LoginParams(
                email = username,
                password = password,
            )
            viewModel.login(loginParams)
        }
    )
}