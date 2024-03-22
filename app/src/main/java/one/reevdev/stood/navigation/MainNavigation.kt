package one.reevdev.stood.navigation

import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavOptions
import androidx.navigation.compose.composable
import one.reevdev.stood.ui.splash.SplashRoute

fun NavController.navigateToSplash(navOptions: NavOptions? = null) {
    this.navigate(MainScreens.Splash.route, navOptions)
}

fun NavGraphBuilder.splashScreen(onProceed: () -> Unit) {
    composable(MainScreens.Splash.route) {
        SplashRoute { onProceed() }
    }
}