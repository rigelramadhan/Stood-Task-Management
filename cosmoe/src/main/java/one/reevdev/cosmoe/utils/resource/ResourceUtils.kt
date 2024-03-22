package one.reevdev.cosmoe.utils.resource

import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

fun <Original, Mapped> Resource<Original>.mapSuccessData(
    mapping: (Original) -> Mapped
): Resource<Mapped> {
    return when (this) {
        is Resource.Success -> {
            Resource.Success(mapping(this.data))
        }

        is Resource.Error -> {
            Resource.Error(this.throwable, this.message)
        }

        is Resource.Loading -> {
            Resource.Loading()
        }
    }
}

fun <Original, Mapped> Flow<Resource<Original>>.mapFlowData(
    mapping: (Original) -> Mapped
): Flow<Resource<Mapped>> {
    return this.map { resource ->
        resource.mapSuccessData {
            mapping(it)
        }
    }
}

fun <T, UiState> Resource<T>.handleResource(
    onSuccess: (T) -> UiState,
    onFailure: (Throwable, String?) -> UiState,
    onLoading: (T?) -> UiState
): UiState {
    return when (this) {
        is Resource.Success -> {
            onSuccess(this.data)
        }

        is Resource.Error -> {
            onFailure(this.throwable, this.message)
        }

        is Resource.Loading -> {
            onLoading(this.data)
        }
    }
}