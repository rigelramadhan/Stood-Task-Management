package one.reevdev.stood.main

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.update
import one.reevdev.cosmoe.eventbus.EventBus
import one.reevdev.cosmoe.eventbus.EventConstants
import one.reevdev.cosmoe.eventbus.event.NameEvent
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor() : ViewModel() {

    private val _isUnauthorized = MutableStateFlow(false)
    val isUnauthorized: StateFlow<Boolean> by lazy { _isUnauthorized }

    fun checkToken() {
        EventBus.subscribe<NameEvent>(
            tag = EventConstants.TAG_AUTH,
            scope = viewModelScope,
        ) {
            if (it.name == EventConstants.EVENT_UNAUTHORIZED) {
                _isUnauthorized.update {
                    true
                }
            }
        }
    }
}