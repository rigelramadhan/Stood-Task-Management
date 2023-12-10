package one.reevdev.stood.features.task.screen.add

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import one.reevdev.stood.core.domain.task.TaskUseCase
import one.reevdev.stood.core.domain.task.mapToApiString
import one.reevdev.stood.core.domain.task.model.TaskPriority
import one.reevdev.stood.core.domain.task.model.TaskTime
import javax.inject.Inject

@HiltViewModel
class AddTaskViewModel @Inject constructor(
    private val taskUseCase: TaskUseCase
) : ViewModel() {

    private val _uiState = MutableStateFlow(AddTaskUiState(false))
    val uiState: StateFlow<AddTaskUiState> by lazy { _uiState }

    fun addTask(title: String, hour: String, date: String, priority: Int) {
        _uiState.update { it.copy(isLoading = true) }
        viewModelScope.launch {
            try {
                taskUseCase.createTask(
                    title = title,
                    priority = TaskPriority.values().first { it.priorityLevel == priority },
                    time = TaskTime(
                        fullISOFormat = mapToApiString(hour, date),
                        time = hour,
                        date = date
                    )
                )
                _uiState.update {
                    it.copy(
                        isLoading = false,
                        errorMessage = null,
                        isTaskSaved = true
                    )
                }
            } catch(e: Exception) {
                _uiState.update {
                    it.copy(
                        isLoading = false,
                        errorMessage = "Something went wrong", // Todo: To be replaced by API message
                        isTaskSaved = false
                    )
                }
            }
        }
    }
}

data class AddTaskUiState(
    val isLoading: Boolean,
    val errorMessage: String? = null,
    val isTaskSaved: Boolean = false
)