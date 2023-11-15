package com.nimble.android.utils

import com.nimble.android.BuildConfig

object Constants {

    const val PRODUCTION_BASE_URL = "https://survey-api.nimblehq.co/api/v1/"
    const val STAGING_BASE_URL = "https://nimble-survey-web-staging.herokuapp.com/api/v1/"
    const val TOKEN_PATH = "oauth/token"
    const val SURVEY_LIST_PATH = "surveys"
    const val REFRESH_TOKEN_PATH = "https://survey-api.nimblehq.co/api/v1/oauth/token"

    const val PAGE = 1
    const val SIZE = 5

    public fun getEndPoint() : String {
        return if (BuildConfig.PROD)
            PRODUCTION_BASE_URL
        else
            STAGING_BASE_URL
    }
}