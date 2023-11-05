package com.nimble.android.repository

import com.nimble.android.api.SurveysApi
import com.nimble.android.api.response.survey.SurveysResponse
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class SurveyRepository {

    suspend fun getSurveysList(token: String, page: Int, size: Int): SurveysResponse {
        lateinit var survey: SurveysResponse
        withContext(Dispatchers.IO) {
            survey = SurveysApi.getSurveysList(token, page, size)
        }
        return survey
    }
}