package com.example.newsapp.viewmodel

import com.example.newsapp.MainCoroutineRule
import com.example.newsapp.data.DummyData
import com.example.newsapp.data.remote.response.TokenResponse
import com.example.newsapp.data.remote.response.toModel
import com.example.newsapp.domain.usecase.LoginUseCase
import com.example.newsapp.presentation.login.LoginViewModel
import com.example.newsapp.utils.ErrorCode
import com.example.newsapp.utils.NetworkThrowable
import com.example.newsapp.utils.Resource
import com.nhaarman.mockitokotlin2.any
import com.nhaarman.mockitokotlin2.mock
import com.nhaarman.mockitokotlin2.times
import com.nhaarman.mockitokotlin2.verify
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.runBlockingTest
import kotlinx.serialization.decodeFromString
import kotlinx.serialization.json.Json
import org.junit.Assert.assertTrue
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.junit.runners.JUnit4
import org.mockito.Mockito

@ExperimentalCoroutinesApi
@RunWith(JUnit4::class)
class LoginViewModelTest {

    private val useCase = mock<LoginUseCase>()
    private val viewModel = LoginViewModel(useCase)
    private val token = Json.decodeFromString<TokenResponse>(DummyData.LOGIN_200).toModel()

    @get:Rule
    val mainCoroutineRule = MainCoroutineRule()

    @Test
    fun `doLogin success returns Resource Success`() = mainCoroutineRule.runBlockingTest {
        Mockito.`when`(useCase.invoke(any(), any())).thenReturn(token)

        mainCoroutineRule.pauseDispatcher()

        viewModel.doLogin()

        assertTrue(viewModel.state.value is Resource.Loading)

        mainCoroutineRule.resumeDispatcher()

        assertTrue(viewModel.state.value is Resource.Success)
        verify(useCase, times(1)).invoke(any(), any())
    }

    @Test
    fun `doLogin fails returns Resource Error`() = mainCoroutineRule.runBlockingTest {
        Mockito.`when`(useCase.invoke(any(), any())).thenAnswer {
            throw NetworkThrowable("Failed to load data", ErrorCode.CodeUnknown)
        }

        mainCoroutineRule.pauseDispatcher()

        viewModel.doLogin()

        assertTrue(viewModel.state.value is Resource.Loading)

        mainCoroutineRule.resumeDispatcher()

        assertTrue(viewModel.state.value is Resource.Error)
        verify(useCase, times(1)).invoke(any(), any())
    }
}