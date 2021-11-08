package com.example.newsapp.datasource

import com.example.newsapp.DummyData
import com.example.newsapp.data.remote.LoginRemoteDataSource
import com.example.newsapp.data.remote.response.TokenResponse
import com.example.newsapp.modules.okhttpTestModule
import com.example.newsapp.modules.remoteSourceTestModule
import com.example.newsapp.modules.retrofitTestModule
import com.example.newsapp.modules.serverModule
import com.example.newsapp.utils.ErrorCode
import com.example.newsapp.utils.NetworkThrowable
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.runBlocking
import kotlinx.serialization.ExperimentalSerializationApi
import kotlinx.serialization.decodeFromString
import kotlinx.serialization.json.Json
import okhttp3.mockwebserver.MockResponse
import okhttp3.mockwebserver.MockWebServer
import org.junit.After
import org.junit.Rule
import org.junit.Test
import org.koin.test.KoinTest
import org.koin.test.KoinTestRule
import org.koin.test.inject
import kotlin.test.assertEquals
import kotlin.test.assertTrue

class LoginRemoteDataSourceTest : KoinTest {

    private val server: MockWebServer by inject()
    private val dataSource: LoginRemoteDataSource by inject()
    private val tokenResponse = Json.decodeFromString<TokenResponse>(DummyData.LOGIN_200)

    @ExperimentalCoroutinesApi
    @ExperimentalSerializationApi
    @get:Rule
    val koinTestRule = KoinTestRule.create {
        printLogger()
        modules(
            listOf(
                serverModule,
                okhttpTestModule,
                retrofitTestModule,
                remoteSourceTestModule
            )
        )
    }

    @ExperimentalCoroutinesApi
    @Test
    fun `login success returns TokenResponse`() = runBlocking {
        server.enqueue(MockResponse().setResponseCode(200).setBody(DummyData.LOGIN_200))
        val response = dataSource.doLogin("tester", "tester123")
        assertEquals(tokenResponse, response)
    }

    @Test
    fun `login fails throws NetworkThrowable`() = runBlocking {
        server.enqueue(MockResponse().setResponseCode(401).setBody(DummyData.BODY_401))

        val response = runCatching {
            dataSource.doLogin("tester", "tester123")
        }.onFailure {
            assertTrue { it is NetworkThrowable }
            assertEquals(ErrorCode.Code401, (it as NetworkThrowable).errorCode)
        }

        assertTrue(response.isFailure)
    }

    @Test
    fun `login fails throws NetworkThrowable with code unknown`() = runBlocking {
        server.enqueue(MockResponse().setBody(""))

        val response = runCatching {
            dataSource.doLogin("tester", "tester123")
        }.onFailure {
            assertEquals(ErrorCode.CodeUnknown, (it as NetworkThrowable).errorCode)
        }

        assertTrue(response.isFailure)
    }

    @Test
    fun `refresh success returns TokenResponse`() = runBlocking {
        server.enqueue(MockResponse().setResponseCode(200).setBody(DummyData.LOGIN_200))
        val response = dataSource.refreshToken()
        assertEquals(tokenResponse, response)
    }

    @Test
    fun `refresh fails throws NetworkThrowable`() = runBlocking {
        server.enqueue(MockResponse().setResponseCode(401).setBody(DummyData.BODY_401))

        val response = runCatching {
            dataSource.refreshToken()
        }.onFailure {
            assertTrue { it is NetworkThrowable }
            assertEquals(ErrorCode.fromInt(401), (it as NetworkThrowable).errorCode)
        }

        assertTrue(response.isFailure)
    }

    @Test
    fun `refresh fails throws NetworkThrowable with code unknown`() = runBlocking {
        server.enqueue(MockResponse().setBody(""))

        val response = runCatching {
            dataSource.refreshToken()
        }.onFailure {
            assertEquals(ErrorCode.CodeUnknown, (it as NetworkThrowable).errorCode)
        }

        assertTrue(response.isFailure)
    }

    @After
    fun tearDown() {
        server.shutdown()
    }
}