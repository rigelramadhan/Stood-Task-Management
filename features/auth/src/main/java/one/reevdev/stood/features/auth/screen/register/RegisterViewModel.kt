package one.reevdev.stood.features.auth.screen.register

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
import one.reevdev.stood.core.domain.auth.AuthUseCase
import one.reevdev.stood.core.domain.auth.params.RegisterParams
import javax.inject.Inject

@HiltViewModel
class RegisterViewModel @Inject constructor(
    private val authUseCase: AuthUseCase
) : ViewModel() {

    private val _uiState = MutableStateFlow(RegisterUiState())
    val uiState: StateFlow<RegisterUiState> by lazy { _uiState }

    fun register(registerParams: RegisterParams) {
        _uiState.update {
            it.copy(
                isLoading = true,
                errorMessage = null,
            )
        }

        viewModelScope.launch {
            authUseCase.register(registerParams)
                .catch { throwable ->
                    _uiState.update {
                        it.copy(
                            isLoading = false,
                            errorMessage = throwable.message.toString(), // TODO: Replace with correct message later
                            isRegisterSuccess = false
                        )
                    }
                }
                .collect { result ->
                    _uiState.update {
                        if (result.displayName.isNotEmpty()) {
                            it.copy(
                                isLoading = false,
                                errorMessage = null,
                                isRegisterSuccess = true
                            )
                        } else {
                            it.copy(
                                isLoading = false,
                                errorMessage = "Something went wrong", // TODO: Replace with correct message later
                                isRegisterSuccess = false
                            )
                        }
                    }
                }
        }
    }
}

data class RegisterUiState(
    override val isLoading: Boolean = false,
    override val errorMessage: String? = null,
    val isRegisterSuccess: Boolean = false,
) : UiState {
    init {
        if (!errorMessage.isNullOrBlank()) {
            Logger.error { errorMessage }
        }
    }
}