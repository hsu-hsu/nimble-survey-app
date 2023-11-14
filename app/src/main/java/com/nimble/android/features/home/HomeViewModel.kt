package com.nimble.android.features.home

import android.os.Build
import androidx.annotation.RequiresApi
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
import timber.log.Timber
import java.time.LocalDateTime
import java.time.format.DateTimeFormatter
import javax.inject.Inject

@RequiresApi(Build.VERSION_CODES.O)
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

    private val _error = MutableLiveData<String>()
    val error: LiveData<String>
        get() = _error

    private var currentPage = Constants.PAGE
    lateinit var todayDate: String

    init {
        getTodayDateAndFormat()
    }

    @RequiresApi(Build.VERSION_CODES.O)
    private fun getTodayDateAndFormat() {
        val current = LocalDateTime.now()
        val formatter = DateTimeFormatter.ofPattern("EEEE, MMMM d")
        todayDate = current.format(formatter)
    }

    fun fetchSurveysList() {
        viewModelScope.launch {
            try {
                val response = repository.getSurveysList(currentPage, Constants.SIZE)
                if(response.isSuccessful) {
                    _surveys.value = response.body()
                }else{
                    Timber.d("Error message: ${response.message()}")
                    _error.value = response.message()
                }
            } catch (e: Exception) {
                Timber.d("Error message: ${e.message}")
                _error.value = e.message
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