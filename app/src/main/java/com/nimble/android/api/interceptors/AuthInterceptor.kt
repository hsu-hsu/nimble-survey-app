package com.nimble.android.api.interceptors

import com.nimble.android.BuildConfig
import com.nimble.android.api.Protected
import com.nimble.android.api.interceptors.AuthInterceptor.Companion.UNAUTHORIZED
import com.nimble.android.api.payloads.RefreshTokenPayload
import com.nimble.android.api.response.token.TokenAttributes
import com.nimble.android.preference.AppSharedPreferences
import com.nimble.android.utils.Constants
import com.nimble.android.utils.SessionManager
import okhttp3.Interceptor
import okhttp3.MediaType.Companion.toMediaType
import okhttp3.Request
import okhttp3.RequestBody.Companion.toRequestBody
import okhttp3.Response
import org.json.JSONObject
import retrofit2.Invocation
import timber.log.Timber
import javax.inject.Inject


class AuthInterceptor @Inject constructor() : Interceptor  {

    companion object {
        const val UNAUTHORIZED = 401
    }


    private val sessionManager = SessionManager()

    override fun intercept(chain: Interceptor.Chain): Response {
        val request = chain.request()
        if (!isProtected(request)) {
            return chain.proceed(request)
        }
        val token = AppSharedPreferences.getAccessToken() // 1
        val tokenExpirationTime = AppSharedPreferences.getExpireIn()
        val originalRequest = chain.request()
        val interceptedRequest: Request
        if (sessionManager.getAccessToken() != null && sessionManager.isAccessTokenExpired()) {
            interceptedRequest =
                chain.createAuthenticatedRequest(AppSharedPreferences.getAccessToken()!!)
        } else {
            val tokenRefreshResponse = chain.refreshToken()
            interceptedRequest = if (tokenRefreshResponse.isSuccessful) {
                val newToken = mapToken(tokenRefreshResponse)
                if (newToken != null) {
                    storeNewToken(newToken)
                    chain.createAuthenticatedRequest(newToken.accessToken!!)
                } else {
                    request }
            } else {
                request }
        }
        return chain
            .proceedDeletingTokenIfUnauthorized(interceptedRequest)

    }

    private fun storeNewToken(newToken: TokenAttributes) {
        AppSharedPreferences.setRefreshToken(newToken.refreshToken)
        AppSharedPreferences.setAccessToken(newToken.accessToken)
        AppSharedPreferences.setExpireIn((System.currentTimeMillis() + newToken.expireIn *1000))
    }

    private fun mapToken(tokenRefreshResponse: Response): TokenAttributes?{
        return try {
            val stringJson = tokenRefreshResponse.body.toString()
            val jsonObject = JSONObject(stringJson)
            val attributes = TokenAttributes(jsonObject.getJSONObject("data").getJSONObject("attributes").getString("access_token"),
                jsonObject.getJSONObject("data").getJSONObject("attributes").getString("token_type"),
                jsonObject.getJSONObject("data").getJSONObject("attributes").getInt("expires_in"),
                jsonObject.getJSONObject("data").getJSONObject("attributes").getString("refresh_token"),
                jsonObject.getJSONObject("data").getJSONObject("attributes").getInt("created_at"))
            attributes
        }catch (e: Exception) {
            null
        }
    }
}


private fun Interceptor.Chain.refreshToken(): Response {
    val body = RefreshTokenPayload("refresh_token", AppSharedPreferences.getRefreshToken()!!,
        BuildConfig.client_id, BuildConfig.client_secret)
    val mediaType = "application/json; charset=utf-8".toMediaType()
    val requestBody = body.toString().toRequestBody(mediaType)

    val refreshToken =
        request()
            .newBuilder()
            .post(requestBody)
            .url(Constants.REFRESH_TOKEN_PATH)
            .build()

    return proceedDeletingTokenIfUnauthorized(refreshToken)
}

private fun Interceptor.Chain.createAuthenticatedRequest(token: String): Request {
    return request().newBuilder().addHeader("Authorization", "Bearer $token").build()
}

private fun Interceptor.Chain.proceedDeletingTokenIfUnauthorized(request: Request): Response {
    val response = proceed(request)

    if (response.code == UNAUTHORIZED) {
        Timber.d("UNAUTHORIZED: 401")
        AppSharedPreferences.deleteToken()
    }

    return response
}

private fun isProtected(request: Request): Boolean {
    val protectedTag =
        request.tag(Invocation::class.java)?.method()?.getAnnotation(Protected::class.java)
    return protectedTag != null
}

//    override fun intercept(chain: Interceptor.Chain): Response {
//        val request = chain.request()
//        var accessToken = sessionManager.getAccessToken()
//
//        val response = chain.proceed(newRequestWithAccessToken(accessToken, request))
//
//        if (response.code() == HttpURLConnection.HTTP_UNAUTHORIZED) {
//            val newAccessToken = sessionManager.getAccessToken()
//            if (newAccessToken != accessToken) {
//                return chain.proceed(newRequestWithAccessToken(accessToken, request))
//            } else {
//                accessToken = refreshToken()!!.data.attributes.accessToken
//                if (accessToken.isNullOrBlank()) {
//                    //sessionManager.logout()
//                    return response
//                }
//                return chain.proceed(newRequestWithAccessToken(accessToken, request))
//            }
//        }
//
//        return response
//    }
//
//    private fun newRequestWithAccessToken(accessToken: String?, request: Request): Request =
//        request.newBuilder()
//            .header("Authorization", "Bearer $accessToken")
//            .build()
//
//    private suspend fun refreshToken(): TokenResponse? {
//        withContext(Dispatchers.IO) {
//            val refreshToken = sessionManager.getRefreshToken()
//            refreshToken?.let {
//                return@let sessionManager.refreshToken(refreshToken)
//            } ?: return@withContext null
//        }
//        return null
//    }
//}

//val accessToken = sessionManager.getAccessToken()
//
//if (accessToken != null && sessionManager.isAccessTokenExpired()) {
//    val refreshToken = sessionManager.getRefreshToken()
//
//    // Make the token refresh request
//    val refreshedToken = runBlocking {
//        val response = apiService.refreshToken(RefreshTokenPayload("refresh_token", sessionManager.getRefreshToken()!!, BuildConfig.client_id,
//            BuildConfig.client_secret))
//        // Update the refreshed access token and its expiration time in the session
//        sessionManager.updateAccessToken(response.data.attributes.accessToken, response.data.attributes.expireIn)
//        response.data.attributes.accessToken
//    }
//
//    if (refreshedToken != null) {
//        // Create a new request with the refreshed access token
//        val newRequest = originalRequest.newBuilder()
//            .header("Authorization", "Bearer $refreshedToken")
//            .build()
//
//        // Retry the request with the new access token
//        return chain.proceed(newRequest)
//    }
//}
//
//// Add the access token to the request header
//val authorizedRequest = originalRequest.newBuilder()
//    .header("Authorization", "Bearer $accessToken")
//    .build()
//
//return chain.proceed(authorizedRequest)