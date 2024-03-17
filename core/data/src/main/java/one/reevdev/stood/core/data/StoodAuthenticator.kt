package one.reevdev.stood.core.data

import okhttp3.Authenticator
import okhttp3.Request
import okhttp3.Response
import okhttp3.Route
import one.reevdev.cosmoe.eventbus.EventBus
import one.reevdev.cosmoe.eventbus.EventConstants
import one.reevdev.cosmoe.eventbus.event.NameEvent
import one.reevdev.stood.core.data.datasource.local.auth.AuthDataStore
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class StoodAuthenticator @Inject constructor(
    private val authDataStore: AuthDataStore
) : Authenticator {
    override fun authenticate(route: Route?, response: Response): Request? {
        val tokenIsNotEmpty = authDataStore.getTokenImmediately().isNotEmpty()
        if (!response.isSuccessful && (response.code == 401 || response.code == 403)) {
            if (tokenIsNotEmpty) {
                synchronized(this) {
                    // TODO: Try catch new request creation for **Refresh Token**
                    EventBus.post(
                        EventConstants.TAG_AUTH,
                        NameEvent(EventConstants.EVENT_UNAUTHORIZED)
                    )
                }
            } else {
                EventBus.post(
                    EventConstants.TAG_AUTH,
                    NameEvent(EventConstants.EVENT_UNAUTHORIZED)
                )
            }
        } else {
            return null
        }
        return null
    }
}