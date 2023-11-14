package com.nimble.android.features.home

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

    private var currentPage = Constants.PAGE

    fun fetchSurveysList() {
        viewModelScope.launch {
            try {
                _surveys.value = repository.getSurveysList(currentPage, Constants.SIZE)
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
        currentPage = 1
    }

    fun loadNextPage() {
        currentPage += 1
        fetchSurveysList()
    }

    fun isLastPage(): Boolean {
        return currentPage == _surveys.value?.meta?.pages
    }
}