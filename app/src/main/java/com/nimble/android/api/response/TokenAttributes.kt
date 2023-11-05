package com.nimble.android.api.response

import com.squareup.moshi.Json

data class TokenAttributes(@Json(name = "access_token") val accessToken: String,
                           @Json(name = "token_type") val tokenType: String,
                           @Json(name = "expires_in") val expireIn: Int,
                           @Json(name = "refresh_token") val refreshToken: String,
                           @Json(name = "created_at") val createdAt: Int)
