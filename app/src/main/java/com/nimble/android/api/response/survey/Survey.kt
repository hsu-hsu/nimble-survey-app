package com.nimble.android.api.response.survey

import android.os.Parcelable
import com.squareup.moshi.Json
import kotlinx.android.parcel.Parcelize

@Parcelize
data class Survey(@Json(name = "id") val id: String,
                  @Json(name = "type") val type: String,
                  @Json(name = "attributes") val attributes: SurveyAttribute) : Parcelable
