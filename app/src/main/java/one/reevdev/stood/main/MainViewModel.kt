package one.reevdev.stood.main

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import one.reevdev.cosmoe.utils.Logger
import one.reevdev.cosmoe.utils.emptyString
import one.reevdev.stood.core.domain.auth.AuthUseCase
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor(private val authUseCase: AuthUseCase) : ViewModel() {

    private val _uiState = MutableStateFlow(emptyString())
    val uiState: StateFlow<String> by lazy { _uiState }

    fun checkToken() {
        viewModelScope.launch {
            authUseCase.getToken()
                .catch {
                    Logger.error(it)
                    _uiState.update { emptyString() }
                }
                .collect { token ->
                    _uiState.update { token }
                }
        }
    }
}