package com.example.newsapp.viewmodel

import com.example.newsapp.MainCoroutineRule
import com.example.newsapp.data.DummyData
import com.example.newsapp.data.local.entity.toModel
import com.example.newsapp.domain.usecase.GetNewsUseCase
import com.example.newsapp.domain.usecase.GetProfileUseCase
import com.example.newsapp.presentation.home.HomeViewModel
import com.example.newsapp.utils.ErrorCode
import com.example.newsapp.utils.NetworkThrowable
import com.example.newsapp.utils.Resource
import com.nhaarman.mockitokotlin2.mock
import com.nhaarman.mockitokotlin2.times
import com.nhaarman.mockitokotlin2.verify
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.runBlockingTest
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.junit.runners.JUnit4
import org.mockito.Mockito
import kotlin.test.assertTrue

@ExperimentalCoroutinesApi
@RunWith(JUnit4::class)
class HomeViewModelTest {

    private val newsUseCase = mock<GetNewsUseCase>()
    private val profileUseCase = mock<GetProfileUseCase>()
    private lateinit var viewModel: HomeViewModel
    private val news = listOf(
        DummyData.NEWS_ENTITY.toModel()
    )
    private val profile = DummyData.PROFILE_ENTITY.toModel()

    @get:Rule
    val mainCoroutineRule = MainCoroutineRule()

    @Test
    fun `getData success returns Resource Success`() = mainCoroutineRule.runBlockingTest {
        Mockito.`when`(newsUseCase.invoke()).thenReturn(news)
        Mockito.`when`(profileUseCase.invoke()).thenReturn(profile)

        mainCoroutineRule.pauseDispatcher()

        viewModel = HomeViewModel(newsUseCase, profileUseCase)

        assertTrue(viewModel.state.value is Resource.Loading)

        mainCoroutineRule.resumeDispatcher()

        assertTrue(viewModel.state.value is Resource.Success)
        verify(newsUseCase, times(1)).invoke()
        verify(profileUseCase, times(1)).invoke()
    }

    @Test
    fun `getData fails returns Resource Error`() = mainCoroutineRule.runBlockingTest {
        Mockito.`when`(newsUseCase.invoke()).thenAnswer {
            throw NetworkThrowable("Failed to lad data", ErrorCode.CodeUnknown)
        }
        Mockito.`when`(profileUseCase.invoke()).thenReturn(profile)

        mainCoroutineRule.pauseDispatcher()

        viewModel = HomeViewModel(newsUseCase, profileUseCase)

        assertTrue(viewModel.state.value is Resource.Loading)

        mainCoroutineRule.resumeDispatcher()

        assertTrue(viewModel.state.value is Resource.Error)
        verify(newsUseCase, times(1)).invoke()
        verify(profileUseCase, times(1)).invoke()
    }
}