package com.nimble.android.api.payloads

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
class RefreshTokenPayload (@Json(name = "grant_type") val grantType : String,
                           @Json(name = "refresh_token")val refreshToken : String,
                           @Json(name = "client_id") val clientId : String,
                           @Json(name = "client_secret") val clientSecret : String)

