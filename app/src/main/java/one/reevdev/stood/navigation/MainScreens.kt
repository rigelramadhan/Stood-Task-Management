package one.reevdev.stood.navigation

import one.reevdev.stood.utils.RouteConstants

sealed class MainScreens(val route: String) {

    data object Splash : MainScreens(RouteConstants.SPLASH_RUOTE)
}