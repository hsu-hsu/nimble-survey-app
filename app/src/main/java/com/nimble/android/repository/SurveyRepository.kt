package com.nimble.android.repository

import com.nimble.android.api.SurveysApiService
import com.nimble.android.api.response.survey.SurveysResponse
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import retrofit2.Response
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class SurveyRepository @Inject constructor(private val webservice: SurveysApiService){

    suspend fun getSurveysList(page: Int, size: Int): Response<SurveysResponse> {
        lateinit var survey: Response<SurveysResponse>
        withContext(Dispatchers.IO) {
            survey = webservice.getSurveysList(page, size)
        }
        return survey
    }
}