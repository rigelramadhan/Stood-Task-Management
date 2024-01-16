package one.reevdev.stood.features.task.screen.list

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.combine
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import one.reevdev.stood.core.domain.task.TaskUseCase
import one.reevdev.stood.core.domain.task.model.Task
import one.reevdev.stood.core.domain.task.model.TaskParams
import one.reevdev.stood.core.domain.task.model.TaskStatus
import one.reevdev.stood.features.task.utils.UiState
import org.threeten.bp.LocalDate
import org.threeten.bp.format.DateTimeFormatter
import java.util.Locale
import javax.inject.Inject

@HiltViewModel
class TaskViewModel @Inject constructor(
    private val taskUseCase: TaskUseCase
) : ViewModel() {

    private val _uiState = MutableStateFlow(TaskUiState(isLoading = true))
    val uiState: StateFlow<TaskUiState> by lazy { _uiState }

    fun init() {
        val currentDate = LocalDate.now()
        val dayOfWeekFormatter = DateTimeFormatter.ofPattern("EEEE", Locale.getDefault())
        val dayFormatter = DateTimeFormatter.ofPattern("dd", Locale.getDefault())
        val monthFormatter = DateTimeFormatter.ofPattern("MMMM", Locale.getDefault())
        val yearFormatter = DateTimeFormatter.ofPattern("yyyy", Locale.getDefault())

        val dayOfWeek = currentDate.format(dayOfWeekFormatter)
        val day = currentDate.format(dayFormatter)
        val month = currentDate.format(monthFormatter)
        val year = currentDate.format(yearFormatter)

        _uiState.update {
            it.copy(
                day = "$dayOfWeek, $day",
                month = month,
                year = year,
            )
        }
    }

    fun getTasks() {
        _uiState.update {
            it.copy(isLoading = true)
        }

        viewModelScope.launch {
            val todoTasksFlow = taskUseCase.getTaskByStatus(TaskStatus.ToDo)
            val onGoingTasksFlow = taskUseCase.getTaskByStatus(TaskStatus.OnGoing)
            val doneTasksFlow = taskUseCase.getTaskByStatus(TaskStatus.Done)

            combine(
                todoTasksFlow,
                onGoingTasksFlow,
                doneTasksFlow
            ) { todoTasks, onGoingTasks, doneTasks ->
                TaskUiState(
                    isLoading = false,
                    errorMessage = null,
                    todoTasks = todoTasks,
                    onGoingTasks = onGoingTasks,
                    doneTasks = doneTasks
                )
            }.catch {
                _uiState.update {
                    it.copy(
                        isLoading = false,
                        errorMessage = "Something went wrong" // TODO: To be replaced by API error message
                    )
                }
            }.collect { uiState ->
                _uiState.update {
                    uiState
                }
            }
        }
    }

    fun setFilter(status: TaskStatus) {
        _uiState.update {
            it.copy(isLoading = true)
        }

        _uiState.update {
            it.copy(
                isLoading = false,
                filter = status
            )
        }
    }

    fun updateTask(task: Task) {
        _uiState.update { it.copy(isLoading = true) }
        val taskParam = TaskParams(
            task.title, task.priority, task.time, task.category.id, task.status
        )

        viewModelScope.launch {
            try {
                taskUseCase.updateTask(task.id, taskParam)
                _uiState.update {
                    it.copy(
                        isLoading = false,
                        errorMessage = null,
                    )
                }
            } catch (e: Exception) {
                _uiState.update {
                    it.copy(
                        isLoading = false,
                        errorMessage = "Something went wrong", // Todo: To be replaced by API message
                    )
                }
            }
        }
    }
}

data class TaskUiState(
    override val isLoading: Boolean = false,
    override val errorMessage: String? = null,
    val todoTasks: List<Task>? = null,
    val onGoingTasks: List<Task>? = null,
    val doneTasks: List<Task>? = null,
    val filter: TaskStatus = TaskStatus.All,
    val day: String? = null,
    val month: String? = null,
    val year: String? = null,
) : UiState