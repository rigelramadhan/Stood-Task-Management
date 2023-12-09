package one.reevdev.stood.features.task.navigation

import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavOptions
import androidx.navigation.NavType
import androidx.navigation.compose.composable
import androidx.navigation.navArgument
import one.reevdev.stood.features.task.screen.add.AddTaskRouter
import one.reevdev.stood.features.task.screen.detail.DetailTaskRouter
import one.reevdev.stood.features.task.screen.list.TaskRouter
import one.reevdev.stood.features.task.utils.RouteConstants

fun NavController.navigateToTask(navOptions: NavOptions? = null) {
    this.navigate(TaskScreens.Task.route, navOptions)
}

fun NavGraphBuilder.taskScreen(onTaskClick: (id: String) -> Unit) {
    composable(
        route = TaskScreens.Task.route,
    ) {
        TaskRouter(onTaskClick = onTaskClick)
    }
}

fun NavController.navigateToDetail(id: String, navOptions: NavOptions? = null) {
    this.navigate(TaskScreens.TaskDetail.createRoute(id), navOptions)
}

fun NavGraphBuilder.taskDetailScreen(onNavigateBack: () -> Unit) {
    composable(
        route = TaskScreens.TaskDetail.route,
        arguments = listOf(navArgument(RouteConstants.ARGUMENT_TASK_ID) {
            type = NavType.StringType
        })
    ) {
        val id = it.arguments?.getString(RouteConstants.ARGUMENT_TASK_ID).orEmpty()

        DetailTaskRouter(taskId = id, onNavigateBack = onNavigateBack)
    }
}

fun NavController.navigateToAddTaskScreen(navOptions: NavOptions? = null) {
    this.navigate(TaskScreens.AddTask.route, navOptions)
}

fun NavGraphBuilder.taskAddTaskScreen(onNavigateBack: () -> Unit) {
    composable(
        route = TaskScreens.AddTask.route,
    ) {
        AddTaskRouter(onNavigateBack = onNavigateBack)
    }
}