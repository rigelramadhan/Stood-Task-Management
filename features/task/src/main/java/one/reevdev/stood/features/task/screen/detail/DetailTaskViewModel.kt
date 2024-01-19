package one.reevdev.stood.features.task.screen.detail

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import one.reevdev.cosmoe.ui.compose.UiState
import one.reevdev.stood.core.domain.task.TaskUseCase
import one.reevdev.stood.core.domain.task.model.Category
import one.reevdev.stood.core.domain.task.model.Task
import one.reevdev.stood.core.domain.task.params.TaskUiParams
import one.reevdev.stood.core.domain.task.toDomain
import javax.inject.Inject

@HiltViewModel
class DetailTaskViewModel @Inject constructor(
    private val taskUseCase: TaskUseCase
) : ViewModel() {

    private val _uiState = MutableStateFlow(DetailUiState(false))
    val uiState: StateFlow<DetailUiState> by lazy { _uiState }

    fun init() {
        _uiState.update { it.copy(isLoading = true) }

        viewModelScope.launch {
            taskUseCase.getCategories()
                .catch {
                    _uiState.update {
                        it.copy(
                            isLoading = false,
                            errorMessage = "Something went wrong", // Todo: To be replaced by API message
                            isTaskSaved = false
                        )
                    }
                }
                .collect { categories ->
                    _uiState.update {
                        it.copy(
                            isLoading = false,
                            errorMessage = null,
                            categories = categories
                        )
                    }
                }
        }
    }

    fun getTaskById(id: String) {
        _uiState.update { it.copy(isLoading = true) }

        viewModelScope.launch {
            taskUseCase.getTaskById(id)
                .catch {
                    _uiState.update {
                        it.copy(
                            isLoading = false,
                            errorMessage = "Something went wrong", // Todo: To be replaced by API message
                        )
                    }
                }
                .collect { task ->
                    _uiState.update {
                        it.copy(
                            isLoading = false,
                            errorMessage = null,
                            task = task
                        )
                    }
                }
        }
    }

    fun deleteTaskById(id: String) {
        _uiState.update { it.copy(isLoading = true) }

        viewModelScope.launch {
            try {
                taskUseCase.deleteTask(id)
                _uiState.update {
                    it.copy(
                        isLoading = false,
                        errorMessage = null,
                        isTaskDeleted = true,
                    )
                }
            } catch (e: Exception) {
                _uiState.update {
                    it.copy(
                        isLoading = false,
                        errorMessage = "Something went wrong", // Todo: To be replaced by API message
                        isTaskDeleted = false,
                    )
                }
            }
        }
    }

    fun updateTask(id: String, taskParam: TaskUiParams) {
        _uiState.update { it.copy(isLoading = true) }

        viewModelScope.launch {
            try {
                taskUseCase.updateTask(id, taskParam.toDomain())
                _uiState.update {
                    it.copy(
                        isLoading = false,
                        errorMessage = null,
                        isTaskSaved = true,
                    )
                }
            } catch (e: Exception) {
                _uiState.update {
                    it.copy(
                        isLoading = false,
                        errorMessage = "Something went wrong", // Todo: To be replaced by API message
                        isTaskDeleted = false,
                        isTaskSaved = false
                    )
                }
            }
        }
    }
}

data class DetailUiState(
    override val isLoading: Boolean,
    override val errorMessage: String? = null,
    val isTaskDeleted: Boolean = false,
    val isTaskSaved: Boolean = false,
    val categories: List<Category> = emptyList(),
    val task: Task? = null
) : UiState