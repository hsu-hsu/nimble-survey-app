package com.nimble.android.api.response

import com.squareup.moshi.Json

data class Token(@Json(name = "id") val id: Int,
                 @Json(name = "type") val type: String,
                 @Json(name = "attributes") val attributes: TokenAttributes)
