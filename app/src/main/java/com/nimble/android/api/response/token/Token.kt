package com.nimble.android.api.response.token

import android.os.Parcelable
import com.squareup.moshi.Json
import kotlinx.android.parcel.Parcelize

@Parcelize
data class Token(@Json(name = "id") val id: Int,
                 @Json(name = "type") val type: String,
                 @Json(name = "attributes") val attributes: TokenAttributes
): Parcelable
