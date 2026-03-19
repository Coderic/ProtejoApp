package org.coderic.protective.mobile.data.remote.model

import com.google.gson.annotations.SerializedName

data class LoginRequest(
    @SerializedName("email") val email: String,
    @SerializedName("password") val password: String
)

data class ApiResponse<T>(
    @SerializedName("success") val success: Boolean,
    @SerializedName("message") val message: String,
    @SerializedName("data") val data: T?,
    @SerializedName("timestamp") val timestamp: String,
    @SerializedName("path") val path: String
)

data class LoginResponseData(
    @SerializedName("userId") val userId: Int,
    @SerializedName("email") val email: String,
    @SerializedName("firstName") val firstName: String,
    @SerializedName("accessToken") val accessToken: String,
    @SerializedName("refreshToken") val refreshToken: String,
    @SerializedName("sessionId") val sessionId: String
)
