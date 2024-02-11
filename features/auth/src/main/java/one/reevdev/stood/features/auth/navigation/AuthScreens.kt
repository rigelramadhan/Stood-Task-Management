package one.reevdev.stood.features.auth.navigation

import one.reevdev.stood.features.auth.utils.RouteConstants

sealed class AuthScreens(val route: String) {

    data object Login : AuthScreens(RouteConstants.LOGIN_ROUTE)

    data object Register : AuthScreens(RouteConstants.REGISTER_ROUTE)
}