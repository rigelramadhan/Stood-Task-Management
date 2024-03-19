package one.reevdev.stood.ui

import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.rememberNavController
import one.reevdev.stood.features.auth.navigation.loginScreen
import one.reevdev.stood.features.auth.navigation.navigateToLogin
import one.reevdev.stood.features.auth.navigation.navigateToRegister
import one.reevdev.stood.features.auth.navigation.registerScreen
import one.reevdev.stood.features.task.navigation.TaskScreens
import one.reevdev.stood.features.task.navigation.navigateToAddTaskScreen
import one.reevdev.stood.features.task.navigation.navigateToDetail
import one.reevdev.stood.features.task.navigation.navigateToTask
import one.reevdev.stood.features.task.navigation.taskAddTaskScreen
import one.reevdev.stood.features.task.navigation.taskDetailScreen
import one.reevdev.stood.features.task.navigation.taskScreen
import one.reevdev.stood.main.MainViewModel

@Composable
fun StoodApp(
    modifier: Modifier = Modifier,
    viewModel: MainViewModel = hiltViewModel(),
    startDestination: String = TaskScreens.Task.route,
    navController: NavHostController = rememberNavController()
) {
    val isUnauthorized by viewModel.isUnauthorized.collectAsStateWithLifecycle()
    LaunchedEffect(true) {
        viewModel.checkToken()
    }

    if (isUnauthorized) {
        navController.navigateToLogin()
    }

    NavHost(
        navController = navController,
        startDestination = startDestination,
        modifier = modifier
    ) {
        // Task
        taskScreen(
            onTaskClick = { navController.navigateToDetail(it) },
            navigateToAddTask = { navController.navigateToAddTaskScreen() },
            onUnauthorized = { navController.navigateToLogin() }
        )
        taskDetailScreen { navController.navigateUp() }
        taskAddTaskScreen { navController.navigateUp() }

        // Auth
        loginScreen(
            navigateToRegister = { navController.navigateToRegister() },
            onLoginSuccess = { navController.navigateToTask() }
        )
        registerScreen(
            navigateToLogin = { navController.navigateToLogin() },
            onRegisterSuccess = { navController.navigateToLogin() }
        )
    }
}