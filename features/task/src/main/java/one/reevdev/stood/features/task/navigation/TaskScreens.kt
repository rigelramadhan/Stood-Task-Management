package one.reevdev.stood.features.task.navigation

import one.reevdev.stood.features.task.utils.RouteConstants

sealed class TaskScreens(val route: String) {
    data object Task : TaskScreens(RouteConstants.TASK_ROUTE)
    data object TaskDetail :
        TaskScreens("${RouteConstants.TASK_DETAIL_ROUTE}/{${RouteConstants.ARGUMENT_TASK_ID}}") {
        fun createRoute(taskId: String) = "${RouteConstants.TASK_DETAIL_ROUTE}/$taskId"
    }

    data object AddTask : TaskScreens(RouteConstants.ADD_TASK_ROUTE)
}