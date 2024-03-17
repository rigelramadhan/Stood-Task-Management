package one.reevdev.cosmoe.eventbus.event

import one.reevdev.cosmoe.eventbus.BaseEvent

data class NameEvent(
    val name: String
) : BaseEvent()
