package com.nimble.android.preference

import android.content.Context
import android.content.Context.MODE_PRIVATE
import android.content.SharedPreferences

object AppSharedPreferences {

    private var sharedPreferences: SharedPreferences? = null
    const val SHARED_PREFS = "APP_SHARED_PREFS"
    const val ACCESS_TOKEN_KEY = "access_token"
    const val REFRESH_TOKEN_KEY = "refresh_token"
    const val EXPIRE_IN = "expire_in"

    fun setup(context: Context) {
        sharedPreferences = context.getSharedPreferences("surveyList.sharedprefs", MODE_PRIVATE)
    }

    fun getAccessToken(): String? = sharedPreferences?.getString(ACCESS_TOKEN_KEY, null)

    fun setAccessToken(accessToken: String) {
        sharedPreferences?.edit()?.putString(ACCESS_TOKEN_KEY, accessToken)?.apply()
    }

    fun getRefreshToken(): String? = sharedPreferences?.getString(REFRESH_TOKEN_KEY, null)

    fun setRefreshToken(refreshToken: String) {
        sharedPreferences?.edit()?.putString(REFRESH_TOKEN_KEY, refreshToken)?.apply()
    }

    fun getExpireIn(): Long? = sharedPreferences?.getLong(EXPIRE_IN, 0)

    fun setExpireIn(expireIn: Long) {
        sharedPreferences?.edit()?.putLong(EXPIRE_IN, expireIn)?.apply()
    }

    fun deleteToken() {
        sharedPreferences?.edit()?.apply {
            remove(ACCESS_TOKEN_KEY)
            remove(REFRESH_TOKEN_KEY)
            remove(ACCESS_TOKEN_KEY)
            apply()
        }
    }
}