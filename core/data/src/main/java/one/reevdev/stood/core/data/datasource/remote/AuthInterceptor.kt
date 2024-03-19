package one.reevdev.stood.core.data.datasource.remote

import okhttp3.Interceptor
import okhttp3.Response
import one.reevdev.stood.core.data.datasource.local.auth.AuthDataStore
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class AuthInterceptor @Inject constructor(
    private val dataStore: AuthDataStore
) : Interceptor {
    override fun intercept(chain: Interceptor.Chain): Response {
        var request = chain.request()
        val token = dataStore.getTokenImmediately()
        request = request.newBuilder()
            .addHeader("Authorization", token)
            .build()
        return chain.proceed(request)
    }
}