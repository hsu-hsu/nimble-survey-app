package com.nimble.android.api.response.survey

import android.os.Parcelable
import com.squareup.moshi.Json
import kotlinx.android.parcel.Parcelize

@Parcelize
data class SurveyMeta(@Json(name = "page") val page: Int,
                      @Json(name = "pages") val pages: Int,
                      @Json(name = "page_size") val pageSize: Int,
                      @Json(name = "records") val records: Int): Parcelable
