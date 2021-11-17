package com.example.newsapp.repository

import com.example.newsapp.data.DummyData
import com.example.newsapp.data.LoginDataSource
import com.example.newsapp.data.LoginRepositoryImpl
import com.example.newsapp.data.remote.response.TokenResponse
import com.example.newsapp.utils.DateUtils
import com.example.newsapp.utils.ErrorCode
import com.example.newsapp.utils.NetworkThrowable
import com.nhaarman.mockitokotlin2.any
import com.nhaarman.mockitokotlin2.mock
import com.nhaarman.mockitokotlin2.times
import com.nhaarman.mockitokotlin2.verify
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.runBlockingTest
import kotlinx.serialization.decodeFromString
import kotlinx.serialization.json.Json
import org.junit.Assert.assertEquals
import org.junit.Assert.assertTrue
import org.junit.Test
import org.junit.runner.RunWith
import org.junit.runners.JUnit4
import org.mockito.Mockito

@ExperimentalCoroutinesApi
@RunWith(JUnit4::class)
class LoginRepositoryImplTest {

    private val localSource = mock<LoginDataSource>()
    private val remoteSource = mock<LoginDataSource>()
    private val dateUtils = mock<DateUtils>()
    private val repository = LoginRepositoryImpl(localSource, remoteSource, dateUtils)
    private val expectedToken = Json.decodeFromString<TokenResponse>(DummyData.LOGIN_200)
    private val refreshToken = expectedToken.copy(scheme = "API KEY")

    //    Tests for isloggedin function
    @Test
    fun `token empty returns false`() {
        Mockito.`when`(localSource.getToken()).thenReturn(null)

        val result = repository.isLoggedIn()

        assertTrue(!result)
        verify(localSource, times(1)).getToken()
    }

    @Test
    fun `expiredAt empty returns false`() {
        Mockito.`when`(localSource.getToken()).thenReturn("token")
        Mockito.`when`(localSource.getExpiredAt()).thenReturn(null)

        val result = repository.isLoggedIn()

        assertTrue(!result)
        verify(localSource, times(1)).getToken()
        verify(localSource, times(1)).getExpiredAt()
    }

    @Test
    fun `token expired returns false`() {
        Mockito.`when`(localSource.getToken()).thenReturn("token")
        Mockito.`when`(localSource.getExpiredAt()).thenReturn("expired at")
        Mockito.`when`(dateUtils.isDateAfterCurrentTime(any())).thenReturn(true)

        val result = repository.isLoggedIn()

        assertTrue(!result)
        verify(localSource, times(1)).getToken()
        verify(localSource, times(1)).getExpiredAt()
        verify(dateUtils, times(1)).isDateAfterCurrentTime(any())
    }

    @Test
    fun `token still available and valid returns true`() {
        Mockito.`when`(localSource.getToken()).thenReturn("token")
        Mockito.`when`(localSource.getExpiredAt()).thenReturn("expired at")
        Mockito.`when`(dateUtils.isDateAfterCurrentTime(any())).thenReturn(false)

        val result = repository.isLoggedIn()

        assertTrue(result)
        verify(localSource, times(1)).getToken()
        verify(localSource, times(1)).getExpiredAt()
        verify(dateUtils, times(1)).isDateAfterCurrentTime(any())
    }

    //    Test for dologin function
    @Test
    fun `dologin success returns token`() = runBlockingTest {
        Mockito.`when`(remoteSource.doLogin(any(), any())).thenReturn(expectedToken)

        val result = repository.doLogin("test", "tester123")

        assertEquals(expectedToken.toModel(), result)
        verify(remoteSource, times(1)).doLogin(any(), any())
        verify(localSource, times(1)).saveSession(any(), any())
    }

    @Test
    fun `doLogin fails returns NetworkThrowable`() = runBlockingTest {
        Mockito.`when`(remoteSource.doLogin(any(), any())).thenAnswer {
            throw NetworkThrowable("Failed to load data", ErrorCode.CodeUnknown)
        }

        val result = runCatching {
            repository.doLogin("tester", "tester123")
        }.onFailure {
            assertTrue(it is NetworkThrowable)
        }

        assertTrue(result.isFailure)
    }

    //    Test for refreshing token function
    @Test
    fun `refresh token success returns token`() = runBlockingTest {
        Mockito.`when`(localSource.getExpiredAt()).thenReturn(expectedToken.expiresAt)
        Mockito.`when`(remoteSource.refreshToken()).thenReturn(refreshToken)
        Mockito.`when`(dateUtils.between1HourAndNow(any())).thenReturn(true)

        val result = repository.refreshToken()

        assertEquals(refreshToken.toModel(), result)
        verify(remoteSource, times(1)).refreshToken()
        verify(localSource, times(1)).getExpiredAt()
        verify(dateUtils, times(1)).between1HourAndNow(any())
    }

    @Test
    fun `refresh token not needed returns current token`() = runBlockingTest {
        Mockito.`when`(localSource.getExpiredAt()).thenReturn(expectedToken.expiresAt)
        Mockito.`when`(localSource.getToken()).thenReturn(expectedToken.token)
        Mockito.`when`(dateUtils.between1HourAndNow(any())).thenReturn(false)

        val result = repository.refreshToken()

        assertEquals(expectedToken.toModel(), result)
        verify(localSource, times(1)).getExpiredAt()
        verify(localSource, times(1)).getToken()
        verify(dateUtils, times(1)).between1HourAndNow(any())
     }

    @Test
    fun `refresh token fails returns previous token`() = runBlockingTest {
        Mockito.`when`(localSource.getExpiredAt()).thenReturn(expectedToken.expiresAt)
        Mockito.`when`(localSource.getToken()).thenReturn(expectedToken.token)
        Mockito.`when`(dateUtils.between1HourAndNow(any())).thenReturn(true)

        val result = repository.refreshToken()

        assertEquals(expectedToken.toModel(), result)
        verify(localSource, times(1)).getExpiredAt()
        verify(localSource, times(1)).getToken()
        verify(dateUtils, times(1)).between1HourAndNow(any())
    }
}