package com.nimble.android.utils

import com.nimble.android.BuildConfig

object Constants {

    var BASE_URL = if(BuildConfig.PROD) "https://survey-api.nimblehq.co/api/v1/" else "https://nimble-survey-web-staging.herokuapp.com/api/v1/"
    const val TOKEN_PATH = "oauth/token"
    const val SURVEY_LIST_PATH = "surveys"
    const val REFRESH_TOKEN_PATH = "https://survey-api.nimblehq.co/api/v1/oauth/token"

    const val PAGE = 1
    const val SIZE = 5
}