package org.coderic.protective.mobile.data.repository

import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import org.coderic.protective.mobile.data.remote.ApiService
import org.coderic.protective.mobile.data.remote.model.ApiResponse
import org.coderic.protective.mobile.data.remote.model.LoginRequest
import org.coderic.protective.mobile.data.remote.model.LoginResponseData

sealed class AuthResult<out T> {
    data class Success<T>(val data: T) : AuthResult<T>()
    data class Error(val message: String) : AuthResult<Nothing>()
}

class AuthRepository(private val apiService: ApiService) {

    suspend fun login(email: String, password: String): AuthResult<LoginResponseData> {
        return try {
            val response = apiService.login(LoginRequest(email, password))
            if (response.isSuccessful) {
                val body = response.body()
                if (body?.success == true && body.data != null) {
                    AuthResult.Success(body.data)
                } else {
                    AuthResult.Error(body?.message ?: "Error al iniciar sesión")
                }
            } else {
                val errorMessage = try {
                    val type = object : TypeToken<ApiResponse<Any>>() {}.type
                    val errorBody = Gson().fromJson<ApiResponse<Any>>(response.errorBody()?.charStream(), type)
                    errorBody?.message
                } catch (e: Exception) { null }
                AuthResult.Error(errorMessage ?: "Error al iniciar sesión")
            }
        } catch (e: Exception) {
            AuthResult.Error("Sin conexión al servidor: ${e.message}")
        }
    }
}
