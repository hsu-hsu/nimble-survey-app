package com.nimble.android.features.login

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.nimble.android.BuildConfig
import com.nimble.android.api.payloads.TokenPayload
import com.nimble.android.api.response.token.TokenResponse
import com.nimble.android.repository.TokenRepository
import io.mockk.coEvery
import io.mockk.coVerify
import io.mockk.mockkClass
import io.mockk.mockkConstructor
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
class LoginViewModelTest {

        private lateinit var tokenRepo : TokenRepository
        private lateinit var viewModel: LoginViewModel
        private val dispatcher: TestDispatcher = TestCoroutineDispatcher()
        @get:Rule
        var rule: TestRule = InstantTaskExecutorRule()

        @Before
        fun setUp() {
            Dispatchers.setMain(dispatcher)
            tokenRepo = mockkClass(TokenRepository::class)
            viewModel = LoginViewModel(tokenRepo)
            mockkConstructor(TokenPayload::class)
        }

        @After
        fun tearDown() {
            Dispatchers.resetMain()
        }


        @Test
        fun onLoginButtonClick_calls_getAuthToken() {

            val tokenResponse = mockkClass(TokenResponse::class)
            val payload = TokenPayload("password", "your_email@example.com",
                "12345678", BuildConfig.client_id, BuildConfig.client_secret)

            coEvery {
                tokenRepo.getAuthToken(payload = payload)
            } returns Response.success(tokenResponse)

            viewModel.onLoginButtonClick()

            coVerify {
                tokenRepo.getAuthToken(payload = payload)
            }
            Assert.assertEquals(viewModel.authToken.value, tokenResponse)
        }
}
