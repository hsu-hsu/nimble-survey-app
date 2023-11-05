package com.nimble.android.api.response.survey

import android.os.Parcelable
import com.squareup.moshi.Json
import kotlinx.android.parcel.Parcelize

@Parcelize
data class SurveysResponse(@Json(name = "data") val data: Survey) : Parcelable