package com.nimble.android.features.home

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.nimble.android.api.response.survey.SurveysResponse
import com.nimble.android.repository.SurveyRepository
import io.mockk.coEvery
import io.mockk.coVerify
import io.mockk.mockkClass
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.TestCoroutineDispatcher
import kotlinx.coroutines.test.TestDispatcher
import kotlinx.coroutines.test.resetMain
import kotlinx.coroutines.test.setMain
import org.junit.After
import org.junit.Assert
import org.junit.Before
import org.junit.Rule

import org.junit.Test
import org.junit.rules.TestRule
import retrofit2.Response

@OptIn(ExperimentalCoroutinesApi::class)
class HomeViewModelTest {

    private lateinit var surveyRepo : SurveyRepository
    private lateinit var viewModel: HomeViewModel
    private val dispatcher: TestDispatcher = TestCoroutineDispatcher()
    @get:Rule
    var rule: TestRule = InstantTaskExecutorRule()

    @Before
    fun setUp() {
        Dispatchers.setMain(dispatcher)
        surveyRepo = mockkClass(SurveyRepository::class)
        viewModel = HomeViewModel(surveyRepo)
    }

    @After
    fun tearDown() {
        Dispatchers.resetMain()
    }
    @Test
    fun when_home_screen_start_should_fetch_surveylist() {
        val surveyResponse = mockkClass(SurveysResponse::class)
        coEvery {
            surveyRepo.getSurveysList(1,5)
        } returns Response.success(surveyResponse)

        viewModel.fetchSurveysList()

        coVerify {
            surveyRepo.getSurveysList(1,5)
        }
        Assert.assertEquals(viewModel.survey.value, surveyResponse)
    }
}