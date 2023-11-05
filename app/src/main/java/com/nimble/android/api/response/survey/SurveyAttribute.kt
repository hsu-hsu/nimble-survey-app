package com.nimble.android.api.response.survey

import android.os.Parcelable
import com.squareup.moshi.Json
import kotlinx.android.parcel.Parcelize

@Parcelize
data class SurveyAttribute(@Json(name = "title") val title: String,
                           @Json(name = "description") val description: String,
                           @Json(name = "cover_image_url") val coverImageUrl: String,
                           @Json(name = "survey_type") val surveyType: String) : Parcelable
