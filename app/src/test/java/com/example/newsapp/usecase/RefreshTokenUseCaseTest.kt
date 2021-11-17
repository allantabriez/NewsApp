package com.example.newsapp.usecase

import com.example.newsapp.data.DummyData
import com.example.newsapp.domain.repository.LoginRepository
import com.example.newsapp.domain.usecase.RefreshTokenUseCase
import com.example.newsapp.utils.ErrorCode
import com.example.newsapp.utils.NetworkThrowable
import com.nhaarman.mockitokotlin2.mock
import com.nhaarman.mockitokotlin2.times
import com.nhaarman.mockitokotlin2.verify
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.runBlockingTest
import org.junit.Assert
import org.junit.Test
import org.junit.runner.RunWith
import org.junit.runners.JUnit4
import org.mockito.Mockito
import kotlin.test.assertTrue

@ExperimentalCoroutinesApi
@RunWith(JUnit4::class)
class RefreshTokenUseCaseTest {

    private val repository: LoginRepository = mock()
    private val useCase = RefreshTokenUseCase(repository)
    private val expectedToken = DummyData.TOKEN


    @Test
    fun `refresh token success returns Token data`() = runBlockingTest {
        Mockito.`when`(repository.refreshToken()).thenReturn(DummyData.TOKEN)

        val result = useCase.invoke()
        Assert.assertEquals(expectedToken, result)
        verify(repository, times(1)).refreshToken()
    }

    @Test
    fun `login fails throws NetworkThrowable`() = runBlockingTest {
        Mockito.`when`(repository.refreshToken())
            .thenAnswer {
                throw NetworkThrowable("Failed to load data", ErrorCode.CodeUnknown)
            }

        val result = runCatching {
            useCase.invoke()
        }.onFailure {
            assertTrue { it is NetworkThrowable }
        }

        assertTrue { result.isFailure }
        verify(repository, times(1)).refreshToken()
    }
}