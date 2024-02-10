package one.reevdev.stood.core.data.di

import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import one.reevdev.stood.core.data.repository.auth.AuthRepository
import one.reevdev.stood.core.data.repository.auth.IAuthRepository
import one.reevdev.stood.core.data.repository.task.ITaskRepository
import one.reevdev.stood.core.data.repository.task.TaskRepository

@Module
@InstallIn(SingletonComponent::class)
interface RepositoryModule {
    @Binds
    fun bindsTaskRepository(taskRepository: TaskRepository): ITaskRepository

    @Binds
    fun bindsAuthRepository(authRepository: AuthRepository): IAuthRepository
}