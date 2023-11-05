package com.nimble.android.api

import com.nimble.android.api.payloads.TokenPayload
import com.nimble.android.api.response.TokenResponse
import com.nimble.android.utils.Constants
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.Header
import retrofit2.http.POST
import retrofit2.http.Query

interface SurveysApiService {

    @POST(Constants.TOKEN_PATH)
    suspend fun loginUser(@Body payload: TokenPayload): TokenResponse

    @GET(Constants.SURVEY_LIST_PATH)
    suspend fun getSurveysList(@Header("Authorization") token: String,
                               @Query("page[number]") page: Int,
                               @Query("page[size") size: Int) : SurveysResponse
}