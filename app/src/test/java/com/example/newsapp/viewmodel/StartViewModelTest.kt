package com.example.newsapp.viewmodel

import com.example.newsapp.domain.usecase.IsLoggedInUseCase
import com.example.newsapp.presentation.start.StartViewModel
import com.nhaarman.mockitokotlin2.mock
import com.nhaarman.mockitokotlin2.times
import com.nhaarman.mockitokotlin2.verify
import org.junit.Assert.assertTrue
import org.junit.Test
import org.junit.runner.RunWith
import org.junit.runners.JUnit4
import org.mockito.Mockito

@RunWith(JUnit4::class)
class StartViewModelTest {

    private val useCase = mock<IsLoggedInUseCase>()
    private val viewModel = StartViewModel(useCase)

    @Test
    fun `user loggedin returns true`() {
        Mockito.`when`(useCase.invoke()).thenReturn(true)

        val result = viewModel.isLoggedIn()

        assertTrue(result)
        verify(useCase, times(1)).invoke()
    }
}