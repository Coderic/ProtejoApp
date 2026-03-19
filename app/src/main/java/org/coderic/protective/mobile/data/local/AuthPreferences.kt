package org.coderic.protective.mobile.data.local

import android.content.Context
import android.content.SharedPreferences
import androidx.core.content.edit

private const val PREFS_NAME = "auth_prefs"
private const val KEY_ACCESS_TOKEN = "access_token"
private const val KEY_REFRESH_TOKEN = "refresh_token"
private const val KEY_SESSION_ID = "session_id"
private const val KEY_USER_ID = "user_id"
private const val KEY_EMAIL = "email"
private const val KEY_FIRST_NAME = "first_name"

class AuthPreferences(context: Context) {

    private val prefs: SharedPreferences =
        context.getSharedPreferences(PREFS_NAME, Context.MODE_PRIVATE)

    fun saveSession(
        accessToken: String,
        refreshToken: String,
        sessionId: String,
        userId: Int,
        email: String,
        firstName: String
    ) {
        prefs.edit {
            putString(KEY_ACCESS_TOKEN, accessToken)
                .putString(KEY_REFRESH_TOKEN, refreshToken)
                .putString(KEY_SESSION_ID, sessionId)
                .putInt(KEY_USER_ID, userId)
                .putString(KEY_EMAIL, email)
                .putString(KEY_FIRST_NAME, firstName)
        }
    }

    fun getAccessToken(): String? = prefs.getString(KEY_ACCESS_TOKEN, null)

    fun getRefreshToken(): String? = prefs.getString(KEY_REFRESH_TOKEN, null)

    fun getSessionId(): String? = prefs.getString(KEY_SESSION_ID, null)

    fun getUserId(): Int = prefs.getInt(KEY_USER_ID, -1)

    fun getEmail(): String? = prefs.getString(KEY_EMAIL, null)

    fun getFirstName(): String? = prefs.getString(KEY_FIRST_NAME, null)

    fun isLoggedIn(): Boolean = getAccessToken() != null

    fun clearSession() {
        prefs.edit { clear() }
    }
}
