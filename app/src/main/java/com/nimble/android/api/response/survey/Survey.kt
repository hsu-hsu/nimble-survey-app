package com.nimble.android.api.response.survey

import com.squareup.moshi.Json

data class Survey(@Json(name = "id") val id: String,
                  @Json(name = "type") val type: String,
                  @Json(name = "attributes") val attributes: SurveyAttribute)
