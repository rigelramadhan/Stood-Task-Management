package one.reevdev.stood.features.task.navigation

import one.reevdev.stood.features.task.utils.RouteConstants

sealed class TaskScreens(val route: String) {
    object Task : TaskScreens(RouteConstants.TASK_ROUTE)
    object TaskDetail : TaskScreens(RouteConstants.TASK_DETAIL_ROUTE) {
        fun createRoute(id: String) = "$route/$id"
    }
    object AddTask : TaskScreens(RouteConstants.ADD_TASK_ROUTE)
}