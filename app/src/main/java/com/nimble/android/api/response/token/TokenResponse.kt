package com.nimble.android.api.response.token

import android.os.Parcelable
import com.squareup.moshi.Json
import kotlinx.android.parcel.Parcelize

@Parcelize
data class TokenResponse(@Json(name = "data") val data: Token): Parcelable
