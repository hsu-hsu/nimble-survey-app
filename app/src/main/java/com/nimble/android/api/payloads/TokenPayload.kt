package com.nimble.android.api.payloads

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class TokenPayload(@Json(name = "grant_type") val grantType : String,
                        val email : String,
                        val password : String,
                        @Json(name = "client_id") val clientId : String,
                        @Json(name = "client_secret") val clientSecret : String)
