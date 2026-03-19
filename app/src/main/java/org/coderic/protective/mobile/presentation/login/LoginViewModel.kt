package org.coderic.protective.mobile.presentation.login

import android.content.Context
import android.content.Intent
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.launch
import org.coderic.protective.mobile.MainActivity
import org.coderic.protective.mobile.data.local.AuthPreferences
import org.coderic.protective.mobile.data.remote.RetrofitClient
import org.coderic.protective.mobile.data.repository.AuthRepository
import org.coderic.protective.mobile.data.repository.AuthResult

sealed class LoginUiState {
    object Idle : LoginUiState()
    object Loading : LoginUiState()
    object Success : LoginUiState()
    data class Error(val message: String) : LoginUiState()
}

class LoginViewModel(
    private val context: Context,
    private val authRepository: AuthRepository = AuthRepository(RetrofitClient.apiService),
    private val authPreferences: AuthPreferences = AuthPreferences(context)
) : ViewModel() {

    private val _uiState = MutableLiveData<LoginUiState>(LoginUiState.Idle)
    val uiState: LiveData<LoginUiState> = _uiState

    fun onLoginButtonClick(email: String, password: String) {
        if (email.isBlank() || password.isBlank()) {
            _uiState.value = LoginUiState.Error("Ingresa tu email y contraseña")
            return
        }

        _uiState.value = LoginUiState.Loading

        viewModelScope.launch {
            when (val result = authRepository.login(email.trim(), password)) {
                is AuthResult.Success -> {
                    val data = result.data
                    authPreferences.saveSession(
                        accessToken = data.accessToken,
                        refreshToken = data.refreshToken,
                        sessionId = data.sessionId,
                        userId = data.userId,
                        email = data.email,
                        firstName = data.firstName
                    )
                    _uiState.value = LoginUiState.Success
                    navigateToMain()
                }
                is AuthResult.Error -> {
                    _uiState.value = LoginUiState.Error(result.message)
                }
            }
        }
    }

    fun clearError() {
        _uiState.value = LoginUiState.Idle
    }

    private fun navigateToMain() {
        val intent = Intent(context, MainActivity::class.java).apply {
            flags = Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK
        }
        context.startActivity(intent)
    }
}
