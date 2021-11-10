package com.example.newsapp.datasource

import com.example.newsapp.data.DummyData
import com.example.newsapp.data.remote.MeRemoteDataSource
import com.example.newsapp.data.remote.response.DataResponse
import com.example.newsapp.data.remote.response.NewsResponse
import com.example.newsapp.data.remote.response.ProfileResponse
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
import org.junit.Assert.*

import org.junit.After
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.junit.runners.JUnit4
import org.koin.test.KoinTest
import org.koin.test.KoinTestRule
import org.koin.test.inject
import kotlin.test.assertTrue

@RunWith(JUnit4::class)
class MeRemoteDataSourceTest: KoinTest {

    private val server: MockWebServer by inject()
    private val dataSource: MeRemoteDataSource by inject()
    private val profile = Json.decodeFromString<ProfileResponse>(DummyData.PROFILE_200)
    private val news = Json.decodeFromString<DataResponse<List<NewsResponse>>>(DummyData.NEWS_200).data

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

    @Test
    fun `fetch profile returns ProfileResponse`() = runBlocking {
        server.enqueue(MockResponse().setResponseCode(200).setBody(DummyData.PROFILE_200))
        val response = dataSource.getProfile()
        assertEquals(profile, response)
    }

    @Test
    fun `fetch profile throws NetworkThrowable`() = runBlocking {
        server.enqueue(MockResponse().setResponseCode(401).setBody(DummyData.BODY_401))
        val response = runCatching {
            dataSource.getProfile()
        }.onFailure {
            assertTrue { it is NetworkThrowable }
            assertEquals(ErrorCode.Code401, (it as NetworkThrowable).errorCode)
        }

        assertTrue(response.isFailure)
    }

    @Test
    fun `fetch profile throws NetworkThrowable code unknown`() = runBlocking {
        server.enqueue(MockResponse().setBody(""))
        val response = runCatching {
            dataSource.getProfile()
        }.onFailure {
            assertEquals(ErrorCode.CodeUnknown, (it as NetworkThrowable).errorCode)
        }

        assertTrue(response.isFailure)
    }

    @Test
    fun `fetch news returns list of NewsResponse`() = runBlocking {
        server.enqueue(MockResponse().setResponseCode(200).setBody(DummyData.NEWS_200))
        val response = dataSource.getNews()
        assertEquals(news, response)
    }

    @Test
    fun `fetch news throws NetworkThrowable`() = runBlocking {
        server.enqueue(MockResponse().setResponseCode(401).setBody(DummyData.BODY_401))
        val response = runCatching {
            dataSource.getNews()
        }.onFailure {
            assertTrue { it is NetworkThrowable }
            assertEquals(ErrorCode.Code401, (it as NetworkThrowable).errorCode)
        }

        assertTrue(response.isFailure)
    }

    @Test
    fun `fetch news throws NetworkThrowable code unknown`() = runBlocking {
        server.enqueue(MockResponse().setResponseCode(99))
        val response = runCatching {
            dataSource.getNews()
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