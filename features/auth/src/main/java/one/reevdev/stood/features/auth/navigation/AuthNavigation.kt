package one.reevdev.stood.features.auth.navigation

import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavOptions
import androidx.navigation.compose.composable
import one.reevdev.stood.features.auth.screen.login.LoginRoute
import one.reevdev.stood.features.auth.screen.register.RegisterRoute

fun NavController.navigateToLogin(navOptions: NavOptions? = null) {
    this.navigate(AuthScreens.Login.route, navOptions)
}

fun NavGraphBuilder.loginScreen(navigateToRegister: () -> Unit, onLoginSuccess: () -> Unit) {
    composable(
        route = AuthScreens.Login.route
    ) {
        LoginRoute(
            onRegisterButtonClick = navigateToRegister,
            onLoginSuccess = onLoginSuccess
        )
    }
}

fun NavController.navigateToRegister(navOptions: NavOptions? = null) {
    this.navigate(AuthScreens.Register.route, navOptions)
}

fun NavGraphBuilder.registerScreen(navigateToLogin: () -> Unit, onRegisterSuccess: () -> Unit) {
    composable(
        route = AuthScreens.Register.route
    ) {
        RegisterRoute(onLoginButtonClick = navigateToLogin, onRegisterSuccess = onRegisterSuccess)
    }
}