package one.reevdev.stood.core.data.di

import android.content.Context
import androidx.room.Room
import androidx.room.migration.Migration
import androidx.sqlite.db.SupportSQLiteDatabase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.SupervisorJob
import kotlinx.coroutines.launch
import one.reevdev.stood.core.data.datasource.local.task.TaskDao
import one.reevdev.stood.core.data.datasource.local.task.TaskDatabase
import one.reevdev.stood.core.data.datasource.local.task.helper.InitialDataSource
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object DatabaseModule {

    @Singleton
    @Provides
    fun provideTaskDatabase(@ApplicationContext context: Context): TaskDatabase {
        val MIGRATION_1_2 = object : Migration(1, 2) {
            override fun migrate(db: SupportSQLiteDatabase) {
                db.execSQL("ALTER TABLE task ADD COLUMN categoryId INTEGER DEFAULT 0")
            }
        }

        return Room.databaseBuilder(context, TaskDatabase::class.java, "task.db")
            .addMigrations(MIGRATION_1_2)
            .build()
    }


    @Provides
    fun provideTaskDao(taskDatabase: TaskDatabase): TaskDao {
        val dao = taskDatabase.taskDao()
        CoroutineScope(SupervisorJob()).launch {
            dao.insertCategories(InitialDataSource.getCategories())
        }
        return dao
    }
}