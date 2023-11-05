package com.nimble.android.api.response.survey

import com.squareup.moshi.Json

data class SurveysResponse(@Json(name = "data") val data: Survey) {
}