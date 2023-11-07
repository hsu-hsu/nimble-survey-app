package com.nimble.android.api

import com.nimble.android.api.payloads.TokenPayload
import com.nimble.android.api.response.survey.SurveysResponse
import com.nimble.android.api.response.token.TokenResponse
import com.nimble.android.utils.Constants
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.Header
import retrofit2.http.POST
import retrofit2.http.Query

interface SurveysApiService {

    @Protected
    @GET(Constants.SURVEY_LIST_PATH)
    suspend fun getSurveysList(@Header("Authorization") token: String,
                               @Query("number") page: Int,
                               @Query("size") size: Int) : SurveysResponse

    @POST(Constants.TOKEN_PATH)
    suspend fun loginUser(@Body payload: TokenPayload): TokenResponse
}