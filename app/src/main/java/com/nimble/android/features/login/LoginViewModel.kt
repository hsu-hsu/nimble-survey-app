package com.nimble.android.features.login

import android.app.Application
import android.util.Log
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.nimble.android.BuildConfig
import com.nimble.android.api.payloads.TokenPayload
import com.nimble.android.api.response.token.TokenResponse
import com.nimble.android.repository.TokenRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import timber.log.Timber
import javax.inject.Inject

@HiltViewModel
class LoginViewModel @Inject constructor(private val repository: TokenRepository): ViewModel() {

    private val _authToken = MutableLiveData<TokenResponse>()
    val authToken: LiveData<TokenResponse>
        get() = _authToken

    private val _navigateToHomeFragment = MutableLiveData<TokenResponse?>()
    val navigateToHomeFragment
        get() = _navigateToHomeFragment

    private val _showLoading = MutableLiveData<Boolean?>()
    val showLoading
        get() = _showLoading

    private val _error = MutableLiveData<String>()
    val error: LiveData<String>
        get() = _error


    fun onLoginButtonClick() {
        _showLoading.value = true
        getAuthToken()
    }

    private fun getAuthToken() {
        viewModelScope.launch {
            try {
                val response = repository.getAuthToken(TokenPayload("password", "hsuyethin@gmail.com",
                    "hsuyeethin", BuildConfig.client_id, BuildConfig.client_secret))
                if(response.isSuccessful) {
                    _authToken.value = response.body()
                    _navigateToHomeFragment.value = _authToken.value
                }else {
                    Timber.d("Error message: ${response.message()}")
                    _error.value = response.message()
                }
            } catch (e: Exception) {
                Timber.d("Error message: ${e.message}")
                _error.value = e.message
            }
        }
    }

    fun onHomeFragmentNavigate() {
        _navigateToHomeFragment.value = null
        _showLoading.value = false
    }
}