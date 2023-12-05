package one.reevdev.stood.core.data.di

import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import one.reevdev.stood.core.data.repository.task.TaskDatastore
import one.reevdev.stood.core.data.repository.task.TaskRepository

@Module
@InstallIn(SingletonComponent::class)
interface DataModule {
    @Binds
    fun bindsTaskRepository(taskRepository: TaskDatastore): TaskRepository
}