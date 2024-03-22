package one.reevdev.stood.features.task.screen.add

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import one.reevdev.cosmoe.ui.compose.UiState
import one.reevdev.cosmoe.utils.Logger
import one.reevdev.cosmoe.utils.resource.handleResource
import one.reevdev.stood.core.domain.task.TaskUseCase
import one.reevdev.stood.core.domain.task.model.Category
import one.reevdev.stood.core.domain.task.params.TaskUiParams
import one.reevdev.stood.core.domain.task.toDomain
import javax.inject.Inject

@HiltViewModel
class AddTaskViewModel @Inject constructor(
    private val taskUseCase: TaskUseCase
) : ViewModel() {

    private val _uiState = MutableStateFlow(AddTaskUiState(false))
    val uiState: StateFlow<AddTaskUiState> by lazy { _uiState }

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
                .collect { data ->
                    _uiState.update { uiState ->
                        data.handleResource(
                            onSuccess = { categories ->
                                uiState.copy(
                                    isLoading = false,
                                    errorMessage = null,
                                    categories = categories
                                )
                            },
                            onFailure = { throwable, message ->
                                Logger.error(throwable)
                                uiState.copy(
                                    isLoading = false,
                                    errorMessage = message,
                                )
                            },
                            onLoading = {
                                uiState.copy(
                                    isLoading = true,
                                )
                            }
                        )
                    }
                }
        }
    }

    fun addTask(taskParams: TaskUiParams) {
        _uiState.update { it.copy(isLoading = true) }
        viewModelScope.launch {
            taskUseCase.createTask(taskParams.toDomain())
                .catch { throwable ->
                    Logger.error(throwable)
                    _uiState.update {
                        it.copy(
                            isLoading = false,
                            errorMessage = "Something went wrong", // Todo: To be replaced by API message
                            isTaskSaved = false
                        )
                    }
                }
                .collect { data ->
                    data.handleResource(
                        onSuccess = {
                            _uiState.update {
                                it.copy(
                                    isLoading = false,
                                    errorMessage = null,
                                    isTaskSaved = true
                                )
                            }
                        },
                        onLoading = {
                            _uiState.update {
                                it.copy(
                                    isLoading = true,
                                    errorMessage = null,
                                )
                            }
                        },
                        onFailure = { throwable, message ->
                            _uiState.update {
                                it.copy(
                                    isLoading = false,
                                    errorMessage = message,
                                )
                            }
                        }
                    )
                }
        }
    }
}

data class AddTaskUiState(
    override val isLoading: Boolean,
    override val errorMessage: String? = null,
    val isTaskSaved: Boolean = false,
    val categories: List<Category> = emptyList(),
) : UiState