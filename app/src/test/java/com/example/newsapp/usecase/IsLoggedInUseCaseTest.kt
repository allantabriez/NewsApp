package com.example.newsapp.usecase

import com.example.newsapp.domain.repository.LoginRepository
import com.example.newsapp.domain.usecase.IsLoggedInUseCase
import com.nhaarman.mockitokotlin2.mock
import com.nhaarman.mockitokotlin2.times
import com.nhaarman.mockitokotlin2.verify
import org.junit.Assert.*
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.junit.runners.JUnit4
import org.mockito.Mockito

@RunWith(JUnit4::class)
class IsLoggedInUseCaseTest {

    private lateinit var repository: LoginRepository
    private lateinit var useCase: IsLoggedInUseCase

    @Before
    fun setup() {
        repository = mock()
        useCase = IsLoggedInUseCase(repository)
    }

    @Test
    fun `repo returns true, use case returns true`() {
        Mockito.`when`(repository.isLoggedIn()).thenReturn(true)

        val result = useCase.invoke()
        assertTrue(result)
        verify(repository, times(1)).isLoggedIn()
    }
}