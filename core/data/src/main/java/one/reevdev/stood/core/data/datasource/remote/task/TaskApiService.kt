package one.reevdev.stood.core.data.datasource.remote.task

import kotlinx.coroutines.flow.Flow
import one.reevdev.stood.core.data.datasource.remote.ApiConfig
import one.reevdev.stood.core.data.datasource.remote.task.model.CategoryListResponse
import one.reevdev.stood.core.data.datasource.remote.task.model.CategoryResponse
import one.reevdev.stood.core.data.datasource.remote.task.model.TaskListResponse
import one.reevdev.stood.core.data.datasource.remote.task.model.TaskResponse
import one.reevdev.stood.core.data.datasource.remote.task.param.TaskParam
import one.reevdev.stood.core.data.datasource.remote.utils.BaseStoodResponse
import retrofit2.http.Body
import retrofit2.http.DELETE
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.PUT
import retrofit2.http.Path

interface TaskApiService {

    @POST("${ApiConfig.API_VERSION}/task")
    fun createTask(@Body taskParam: TaskParam): Flow<BaseStoodResponse>

    @GET("${ApiConfig.API_VERSION}/task")
    fun getTaskList(): Flow<TaskListResponse>

    @GET("${ApiConfig.API_VERSION}/task/{id}")
    fun getTaskById(@Path("id") id: String): Flow<TaskResponse>

    @PUT("${ApiConfig.API_VERSION}/task/{id}")
    fun updateTask(
        @Path("id") id: String,
        @Body taskParam: TaskParam
    ): Flow<BaseStoodResponse>

    @DELETE("${ApiConfig.API_VERSION}/task/{id}")
    fun deleteTask(@Path("id") id: String): Flow<BaseStoodResponse>

    @GET("${ApiConfig.API_VERSION}/category")
    fun getCategoryList(): Flow<CategoryListResponse>

    @GET("${ApiConfig.API_VERSION}/categoryById/{id}")
    fun getCategoryById(@Path("id") id: Int): Flow<CategoryResponse>
}