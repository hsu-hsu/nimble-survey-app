package com.nimble.android.api.payloads

import com.squareup.moshi.Json

data class TokenPayload(@field:Json(name = "grant_type") val grantType : String,
                        @field:Json(name = "email") val email : String,
                        @field:Json(name = "password") val password : String,
                        @field:Json(name = "client_id") val clientId : String,
                        @field:Json(name = "client_secret") val clientSecret : String)
