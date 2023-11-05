package com.nimble.android.home

import android.app.Application
import android.util.Log
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.nimble.android.BuildConfig
import com.nimble.android.api.payloads.TokenPayload
import com.nimble.android.api.response.survey.SurveysResponse
import com.nimble.android.repository.SurveyRepository
import com.nimble.android.utils.Constants
import kotlinx.coroutines.launch

class HomeViewModel(application: Application): AndroidViewModel(application) {

    private val repository = SurveyRepository()

    private val _surveys = MutableLiveData<SurveysResponse>()
    val survey: LiveData<SurveysResponse>
        get() = _surveys

    init {
        fetchSurveysList("aaa")
    }

    private fun fetchSurveysList(token: String) {
        viewModelScope.launch {
            try {
                _surveys.value = repository.getSurveysList(token, Constants.PAGE, Constants.SIZE)
                Log.i("token", "here is token"+ _surveys.value!!.data.type)
            } catch (e: Exception) {
                e.printStackTrace()
            }

        }
    }
}