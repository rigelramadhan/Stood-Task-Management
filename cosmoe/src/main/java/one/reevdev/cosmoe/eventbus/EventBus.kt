package one.reevdev.cosmoe.eventbus

import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch

object EventBus {

    private val _bus = MutableSharedFlow<Pair<String, BaseEvent>>()
    val bus = _bus.asSharedFlow()

    private val scope = CoroutineScope(Dispatchers.IO)

    fun post(tag: String, event: BaseEvent) {
        val value = Pair(tag, event)
        scope.launch {
            _bus.emit(value)
        }
    }

    inline fun <reified T : BaseEvent> subscribe(
        scope: CoroutineScope = CoroutineScope(Dispatchers.Default),
        tag: String,
        crossinline action: (T) -> Unit,
    ) {
        scope.launch {
            bus.collectLatest {
                if (tag == it.first && it.second is T) {
                    action(it.second as T)
                }
            }
        }
    }
}