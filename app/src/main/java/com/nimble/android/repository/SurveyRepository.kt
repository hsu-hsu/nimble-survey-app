package com.nimble.android.repository

import com.nimble.android.api.SurveysApi
import com.nimble.android.api.response.survey.SurveysResponse
import com.nimble.android.api.response.token.TokenResponse
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class SurveyRepository {

    suspend fun getSurveysList(token: String, page: Int, size: Int): SurveysResponse {
        lateinit var token: TokenResponse
        withContext(Dispatchers.IO) {
            token = SurveysApi.(payload)
        }
        return token
    }
}