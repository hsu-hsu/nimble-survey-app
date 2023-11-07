package com.nimble.android.home

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.nimble.android.api.response.survey.Survey
import com.nimble.android.api.response.survey.SurveysResponse
import com.nimble.android.api.response.token.TokenResponse
import com.nimble.android.repository.SurveyRepository
import com.nimble.android.utils.Constants
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(private val repository: SurveyRepository): ViewModel() {

    private val _surveys = MutableLiveData<SurveysResponse?>()
    val survey: LiveData<SurveysResponse?>
        get() = _surveys

    private val _token = MutableLiveData<TokenResponse>()
    val token: LiveData<TokenResponse>
        get() = _token

    private val _navigateToDetailFragment = MutableLiveData<Survey?>()
    val navigateToDetailFragment
        get() = _navigateToDetailFragment

    fun getTokenFromLogin(token: TokenResponse) {
        _token.value = token
        fetchSurveysList("Bearer " + token.data.attributes.accessToken)
    }

    private fun fetchSurveysList(token: String) {
        viewModelScope.launch {
            try {
                _surveys.value = repository.getSurveysList(token, Constants.PAGE, Constants.SIZE)
                Log.i("survey", "here is survey"+ _surveys.value!!.data[0].type)
            } catch (e: Exception) {
                e.printStackTrace()
            }

        }
    }

    fun onSurveyItemClick(data: Survey) {
        _navigateToDetailFragment.value = data
    }

    fun onDetailFragmentNavigate() {
        _navigateToDetailFragment.value = null
        _surveys.value = null
    }
}