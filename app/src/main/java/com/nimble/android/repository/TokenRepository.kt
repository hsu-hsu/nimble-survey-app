package com.nimble.android.repository

import com.nimble.android.api.SurveysApiService
import com.nimble.android.api.payloads.RefreshTokenPayload
import com.nimble.android.api.payloads.TokenPayload
import com.nimble.android.api.response.token.TokenResponse
import com.nimble.android.preference.AppSharedPreferences
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class TokenRepository @Inject constructor(private val webservice: SurveysApiService) {

    suspend fun getAuthToken(payload: TokenPayload): TokenResponse {
        lateinit var token: TokenResponse
        withContext(Dispatchers.IO) {
            token = webservice.loginUser(payload)
        }

        AppSharedPreferences.setAccessToken(token.data.attributes.accessToken)
        AppSharedPreferences.setRefreshToken(token.data.attributes.refreshToken)
        AppSharedPreferences.setExpireIn(System.currentTimeMillis() + token.data.attributes.expireIn * 1000)
        return token
    }
}