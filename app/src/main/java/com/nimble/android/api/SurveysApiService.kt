package com.nimble.android.api

import com.nimble.android.api.payloads.TokenPayload
import com.nimble.android.api.response.TokenResponse
import com.nimble.android.utils.Constants
import retrofit2.http.Body
import retrofit2.http.POST

interface SurveysApiService {

    @POST(Constants.TOKEN_PATH)
    suspend fun loginUser(@Body payload: TokenPayload): TokenResponse
}