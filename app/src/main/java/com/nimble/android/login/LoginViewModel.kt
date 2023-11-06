package com.nimble.android.login

import android.app.Application
import android.util.Log
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.nimble.android.BuildConfig
import com.nimble.android.api.payloads.TokenPayload
import com.nimble.android.api.response.token.TokenResponse
import com.nimble.android.repository.TokenRepository
import kotlinx.coroutines.launch

class LoginViewModel(application: Application): AndroidViewModel(application) {

    private val repository = TokenRepository()

    private val _authToken = MutableLiveData<TokenResponse>()
    val authToken: LiveData<TokenResponse>
        get() = _authToken

    private val _navigateToHomeFragment = MutableLiveData<TokenResponse?>()
    val navigateToHomeFragment
        get() = _navigateToHomeFragment


    fun onLoginButtonClick() {
        getAuthToken()
    }

    private fun getAuthToken() {
        viewModelScope.launch {
            try {
                _authToken.value = repository.getAuthToken(TokenPayload("password", "your_email@example.com",
                    "12345678", BuildConfig.client_id, BuildConfig.client_secret))
                Log.i("token", "here is token"+ _authToken.value!!.data.type)
                _navigateToHomeFragment.value = _authToken.value
            } catch (e: Exception) {
                e.printStackTrace()
            }
        }
    }

    fun onHomeFragmentNavigate() {
        _navigateToHomeFragment.value = null
    }
}