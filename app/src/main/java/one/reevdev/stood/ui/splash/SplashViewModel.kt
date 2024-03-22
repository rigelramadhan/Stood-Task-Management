package one.reevdev.stood.ui.splash

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
import javax.inject.Inject

@HiltViewModel
class SplashViewModel @Inject constructor(
    private val authUseCase: AuthUseCase,
) : ViewModel() {

    private val _uiState = MutableStateFlow(SplashUiState())
    val uiState: StateFlow<SplashUiState> by lazy { _uiState }

    init {
        viewModelScope.launch {
            authUseCase.getToken()
                .catch { throwable ->
                    Logger.error(throwable)
                    _uiState.update {
                        it.copy(
                            isLoading = false,
                            errorMessage = throwable.message.toString(),
                            shouldProceed = false,
                        )
                    }
                }
                .collect {
                    _uiState.update {
                        it.copy(
                            isLoading = false,
                            errorMessage = null,
                            shouldProceed = true,
                        )
                    }
                }
        }
    }
}

data class SplashUiState(
    override val isLoading: Boolean = false,
    override val errorMessage: String? = null,
    val shouldProceed: Boolean = false,
) : UiState