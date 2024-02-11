package one.reevdev.stood.features.auth.screen.login

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import one.reevdev.cosmoe.ui.compose.UiState
import one.reevdev.stood.core.domain.auth.AuthUseCase
import one.reevdev.stood.core.domain.auth.params.LoginParams
import javax.inject.Inject

@HiltViewModel
class LoginViewModel @Inject constructor(
    private val authUseCase: AuthUseCase,
) : ViewModel() {

    private val _uiState = MutableStateFlow(LoginUiState())
    val uiState: StateFlow<LoginUiState> by lazy { _uiState }

    fun login(loginParams: LoginParams) {
        _uiState.update {
            it.copy(isLoading = true)
        }

        viewModelScope.launch {
            authUseCase.login(loginParams)
                .catch {
                    _uiState.update {
                        it.copy(
                            isLoading = false,
                            errorMessage = "Something went wrong.", // TODO: Update later
                            isLoginSuccess = false,
                        )
                    }
                }
                .collect { result ->
                    if (result.accessToken.isNotEmpty())
                        _uiState.update {
                            it.copy(
                                isLoading = false,
                                errorMessage = null,
                                isLoginSuccess = true,
                            )
                        }
                    else
                        _uiState.update {
                            it.copy(
                                isLoading = false,
                                errorMessage = "Something went wrong.", // TODO: Update later
                                isLoginSuccess = false,
                            )
                        }
                }
        }
    }
}

data class LoginUiState(
    override val isLoading: Boolean = false,
    override val errorMessage: String? = null,
    val isLoginSuccess: Boolean = false,
) : UiState