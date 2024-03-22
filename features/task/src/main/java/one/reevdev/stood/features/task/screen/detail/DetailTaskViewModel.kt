package one.reevdev.stood.features.task.screen.detail

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.combine
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import one.reevdev.cosmoe.ui.compose.UiState
import one.reevdev.cosmoe.utils.Logger
import one.reevdev.cosmoe.utils.emptyString
import one.reevdev.cosmoe.utils.resource.handleResource
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

    fun getInitialData(taskId: String) {
        _uiState.update { it.copy(isLoading = true) }

        viewModelScope.launch {
            val getTaskByIdFlow = taskUseCase.getTaskById(taskId)
            val getCategoriesFlow = taskUseCase.getCategories()

            getTaskByIdFlow.combine(getCategoriesFlow) { task, categories ->
                Pair(task, categories)
            }.catch {
                _uiState.update {
                    it.copy(
                        isLoading = false,
                        errorMessage = "Something went wrong", // Todo: To be replaced by API message
                    )
                }
            }
                .collect { data ->
                    val task = data.first.handleResource(
                        onSuccess = { task ->
                            Triple(task, emptyString(), false)
                        },
                        onFailure = { throwable, message ->
                            Triple(null, message.orEmpty(), false)
                        },
                        onLoading = {
                            Triple(it, emptyString(), true)
                        }
                    )

                    val categories = data.second.handleResource(
                        onSuccess = { categories ->
                            Triple(categories, emptyString(), false)
                        },
                        onFailure = { throwable, message ->
                            Triple(emptyList(), message.orEmpty(), true)
                        },
                        onLoading = {
                            Triple(it.orEmpty(), emptyString(), false)
                        }
                    )

                    _uiState.update { uiState ->
                        uiState.copy(
                            isLoading = task.third || categories.third,
                            errorMessage = task.second.ifEmpty { categories.second.ifEmpty { null } },
                            task = task.first,
                            categories = categories.first
                        )
                    }
                }
        }
    }

    fun deleteTaskById(id: String) {
        _uiState.update { it.copy(isLoading = true) }

        viewModelScope.launch {
            taskUseCase.deleteTask(id)
                .catch {
                    Logger.error(it)
                    _uiState.update {
                        it.copy(
                            isLoading = false,
                            errorMessage = "Something went wrong", // Todo: To be replaced by API message
                            isTaskDeleted = false,
                        )
                    }
                }
                .collect {
                    _uiState.update {
                        it.copy(
                            isLoading = false,
                            errorMessage = null,
                            isTaskDeleted = true,
                        )
                    }
                }
        }
    }

    fun updateTask(id: String, taskParam: TaskUiParams) {
        _uiState.update { it.copy(isLoading = true) }

        viewModelScope.launch {
            taskUseCase.updateTask(id, taskParam.toDomain())
                .catch {
                    Logger.error(it)
                    _uiState.update {
                        it.copy(
                            isLoading = false,
                            errorMessage = "Something went wrong", // Todo: To be replaced by API message
                            isTaskDeleted = false,
                            isTaskSaved = false
                        )
                    }
                }
                .collect {
                    _uiState.update {
                        it.copy(
                            isLoading = false,
                            errorMessage = null,
                            isTaskSaved = true,
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