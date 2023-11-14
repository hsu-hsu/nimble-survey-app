package com.nimble.android.utils

import com.nimble.android.preference.AppSharedPreferences
import javax.inject.Inject

class SessionManager {
    // Other session management properties and methods

    private var accessTokenExpirationTime: Long? = null

    // Method to check if the access token has expired
    fun isAccessTokenExpired(): Boolean {
        accessTokenExpirationTime = AppSharedPreferences.getExpireIn()
        val currentTimeMillis = System.currentTimeMillis()
        return accessTokenExpirationTime != null && currentTimeMillis >= accessTokenExpirationTime!!
    }

    fun getAccessToken(): String? = AppSharedPreferences.getAccessToken()

    fun getRefreshToken(): String? = AppSharedPreferences.getRefreshToken()

}