package com.nimble.android.login

import android.app.Application
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.nimble.android.repository.TokenRepository

//class LoginViewModelFactory (
//    private val application: Application, private val webservice: TokenRepository
//) : ViewModelProvider.Factory {
//    @Suppress("unchecked_cast")
//    override fun <T : ViewModel> create(modelClass: Class<T>): T {
//        if (modelClass.isAssignableFrom(LoginViewModel::class.java)) {
//            return LoginViewModel( application, webservice) as T
//        }
//        throw IllegalArgumentException("Unknown ViewModel class")
//    }
//}