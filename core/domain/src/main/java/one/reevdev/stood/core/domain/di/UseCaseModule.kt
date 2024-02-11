package one.reevdev.stood.core.domain.di

import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import one.reevdev.stood.core.data.datasource.remote.AuthInterceptor
import one.reevdev.stood.core.domain.auth.AuthUseCase
import one.reevdev.stood.core.domain.task.TaskInteractor
import one.reevdev.stood.core.domain.task.TaskUseCase

@Module
@InstallIn(SingletonComponent::class)
interface UseCaseModule {

    @Binds
    fun bindsTaskUseCase(taskInteractor: TaskInteractor): TaskUseCase

    @Binds
    fun bindsAuthUseCase(authInterceptor: AuthInterceptor): AuthUseCase
}