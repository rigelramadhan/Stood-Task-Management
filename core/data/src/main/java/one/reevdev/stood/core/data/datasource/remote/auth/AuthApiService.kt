package one.reevdev.stood.core.data.datasource.remote.auth

import kotlinx.coroutines.flow.Flow
import one.reevdev.stood.core.data.datasource.remote.ApiConfig
import one.reevdev.stood.core.data.datasource.remote.auth.model.LoginResponse
import one.reevdev.stood.core.data.datasource.remote.auth.model.RegisterResponse
import one.reevdev.stood.core.data.datasource.remote.auth.param.LoginParam
import one.reevdev.stood.core.data.datasource.remote.auth.param.RegisterParam
import retrofit2.http.Body
import retrofit2.http.POST

interface AuthApiService {

    @POST("auth/${ApiConfig.API_VERSION}/login")
    fun login(@Body loginParam: LoginParam): Flow<LoginResponse>

    @POST("public/${ApiConfig.API_VERSION}/register")
    fun register(@Body registerParam: RegisterParam): Flow<RegisterResponse>
}