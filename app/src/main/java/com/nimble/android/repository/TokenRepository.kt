package com.nimble.android.repository

import com.nimble.android.api.SurveysApiService
import com.nimble.android.api.payloads.TokenPayload
import com.nimble.android.api.response.token.TokenResponse
import com.nimble.android.preference.AppSharedPreferences
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import retrofit2.Response
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class TokenRepository @Inject constructor(private val webservice: SurveysApiService) {

    suspend fun getAuthToken(payload: TokenPayload): Response<TokenResponse> {
        lateinit var token: Response<TokenResponse>
        withContext(Dispatchers.IO) {
            token = webservice.loginUser(payload)
        }
        if(token.isSuccessful) {
            val responseToken = token.body()!!
            AppSharedPreferences.setAccessToken(responseToken.data.attributes.accessToken)
            AppSharedPreferences.setRefreshToken(responseToken.data.attributes.refreshToken)
            AppSharedPreferences.setExpireIn(System.currentTimeMillis() + responseToken.data.attributes.expireIn * 1000)
        }
        return token
    }
}