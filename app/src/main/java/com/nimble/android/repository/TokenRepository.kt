package com.nimble.android.repository

import com.nimble.android.api.SurveysApi
import com.nimble.android.api.payloads.TokenPayload
import com.nimble.android.api.response.token.TokenResponse
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class TokenRepository {

    suspend fun getAuthToken(payload: TokenPayload): TokenResponse {
        lateinit var token: TokenResponse
        withContext(Dispatchers.IO) {
            token = SurveysApi.getAuthToken(payload)
        }
        return token
    }
}