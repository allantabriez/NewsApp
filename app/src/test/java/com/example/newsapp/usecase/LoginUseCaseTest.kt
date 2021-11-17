package com.example.newsapp.usecase

import com.example.newsapp.data.DummyData
import com.example.newsapp.domain.repository.LoginRepository
import com.example.newsapp.domain.usecase.LoginUseCase
import com.example.newsapp.utils.ErrorCode
import com.example.newsapp.utils.NetworkThrowable
import com.nhaarman.mockitokotlin2.mock
import com.nhaarman.mockitokotlin2.times
import com.nhaarman.mockitokotlin2.verify
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.runBlockingTest
import org.junit.Assert.assertEquals
import org.junit.Test
import org.junit.runner.RunWith
import org.junit.runners.JUnit4
import org.mockito.Mockito
import kotlin.test.assertTrue

@ExperimentalCoroutinesApi
@RunWith(JUnit4::class)
class LoginUseCaseTest {

    private val repository: LoginRepository = mock()
    private val useCase = LoginUseCase(repository)
    private val username: String = "tester"
    private val pass: String = "tester123"
    private val expectedToken = DummyData.TOKEN

    @Test
    fun `login success returns Token data`() = runBlockingTest {
        Mockito.`when`(repository.doLogin(username, pass)).thenReturn(DummyData.TOKEN)

        val result = useCase.invoke(username, pass)
        assertEquals(expectedToken, result)
        verify(repository, times(1)).doLogin(username, pass)
    }

    @Test
    fun `login fails throws NetworkThrowable`() = runBlockingTest {
        Mockito.`when`(repository.doLogin(username, pass))
            .thenAnswer {
                throw NetworkThrowable("Failed to load data", ErrorCode.CodeUnknown)
            }

        val result = runCatching {
            useCase.invoke(username, pass)
        }.onFailure {
            assertTrue { it is NetworkThrowable }
        }

        assertTrue { result.isFailure }
        verify(repository, times(1)).doLogin(username, pass)
    }
}