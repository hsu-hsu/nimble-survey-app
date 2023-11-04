package com.nimble.android.api.response

import com.squareup.moshi.Json

data class TokenResponse(@Json(name = "data") val data: Token)
