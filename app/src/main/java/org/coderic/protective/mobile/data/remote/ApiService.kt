package org.coderic.protective.mobile.data.remote

import org.coderic.protective.mobile.data.remote.model.ApiResponse
import org.coderic.protective.mobile.data.remote.model.LoginRequest
import org.coderic.protective.mobile.data.remote.model.LoginResponseData
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.POST

interface ApiService {

    @POST("v1/auth/login")
    suspend fun login(
        @Body request: LoginRequest
    ): Response<ApiResponse<LoginResponseData>>
}
