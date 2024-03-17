package one.reevdev.stood.core.data.utils

import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.emitAll
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.map
import one.reevdev.cosmoe.utils.resource.Resource

inline fun <ResultType, RequestType> networkBoundResource(
    crossinline query: () -> Flow<ResultType>,
    crossinline fetch: suspend () -> RequestType,
    crossinline saveFetchResult: suspend (RequestType) -> Unit,
    crossinline shouldFetch: (ResultType) -> Boolean = { true }
) = flow {
    val data = query().first()
    emit(Resource.Loading(data))

    val resource: Flow<Resource<ResultType>> = if (shouldFetch(data)) {
        try {
            val resultType = fetch()
            saveFetchResult(resultType)
            query().map { Resource.Success(it) }
        } catch (throwable: Throwable) {
            query().map { Resource.Error(throwable, throwable.message) }
        }
    } else {
        query().map { Resource.Success(it) }
    }

    emitAll(resource)
}