package one.reevdev.stood.features.task.screen.list

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import one.reevdev.stood.core.domain.task.TaskUseCase
import one.reevdev.stood.core.domain.task.model.Task
import javax.inject.Inject

@HiltViewModel
class TaskViewModel @Inject constructor(
    private val taskUseCase: TaskUseCase
) : ViewModel() {

    private val _uiState = MutableStateFlow(TaskUiState(isLoading = true))
    val uiState: StateFlow<TaskUiState> by lazy { _uiState }

    fun getTasks() {
        _uiState.update {
            it.copy(isLoading = true)
        }

        viewModelScope.launch {
            taskUseCase.getTasks()
                .catch {
                    _uiState.update {
                        it.copy(
                            isLoading = false,
                            error = "Something went wrong" // TODO: To be replaced by API error message
                        )
                    }
                }
                .collect { tasks ->
                    _uiState.update {
                        it.copy(
                            isLoading = false,
                            error = null,
                            tasks = tasks
                        )
                    }
                }
        }
    }
}

data class TaskUiState(
    val isLoading: Boolean = false,
    val error: String? = null,
    val tasks: List<Task>? = null
)