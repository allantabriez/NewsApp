package com.example.newsapp.repository

import com.example.newsapp.data.DummyData
import com.example.newsapp.data.MeRepositoryImpl
import com.example.newsapp.data.local.entity.toModel
import com.example.newsapp.data.remote.response.DataResponse
import com.example.newsapp.data.remote.response.NewsResponse
import com.example.newsapp.data.remote.response.ProfileResponse
import com.example.newsapp.domain.model.News
import com.example.newsapp.domain.model.Profile
import com.example.newsapp.repository.fake.FakeErrorMeDataSource
import com.example.newsapp.repository.fake.FakeSuccessMeDataSource
import com.example.newsapp.utils.ErrorCode
import com.example.newsapp.utils.NetworkThrowable
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.runBlockingTest
import kotlinx.serialization.decodeFromString
import kotlinx.serialization.json.Json
import org.junit.Assert.assertEquals
import org.junit.Assert.assertTrue
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.junit.runners.JUnit4

@ExperimentalCoroutinesApi
@RunWith(JUnit4::class)
class MeRepositoryImplTest {

    private val expectedRemoteProfile: Profile =
        Json.decodeFromString<ProfileResponse>(DummyData.PROFILE_200).toModel()
    private val expectedRemoteNews: List<News> =
        Json.decodeFromString<DataResponse<List<NewsResponse>>>(DummyData.NEWS_200).data
            .map { it.toModel() }
    private val expectedLocalProfile = DummyData.PROFILE_ENTITY.toModel()
    private val expectedLocalNews = DummyData.NEWS_ENTITY.toModel()

    private val remoteRepo = MeRepositoryImpl(
        localSource = FakeSuccessMeDataSource(),
        remoteSource = FakeSuccessMeDataSource()
    )
    private val localRepo = MeRepositoryImpl(
        localSource = FakeSuccessMeDataSource(),
        remoteSource = FakeErrorMeDataSource(ErrorCode.CodeUnknown)
    )
    private val emptyRepo = MeRepositoryImpl(
        localSource = FakeErrorMeDataSource(ErrorCode.CodeUnknown),
        remoteSource = FakeErrorMeDataSource(ErrorCode.CodeUnknown)
    )

    private val errorRepo = MeRepositoryImpl(
        localSource = FakeErrorMeDataSource(ErrorCode.Code401),
        remoteSource = FakeErrorMeDataSource(ErrorCode.Code401)
    )

    @Before
    fun setup() {

    }

    @Test
    fun `should return profile from network`() = runBlockingTest {
        val result = runCatching { remoteRepo.getProfile() }

        assertTrue(result.isSuccess)
        assertEquals(expectedRemoteProfile, result.getOrNull())
    }

    @Test
    fun `should return news from network`() = runBlockingTest {
        val result = runCatching { remoteRepo.getNews() }

        assertTrue(result.isSuccess)
        assertEquals(expectedRemoteNews.size, result.getOrNull()?.size)
        assertEquals(expectedRemoteNews.first(), result.getOrNull()?.first())
    }

    @Test
    fun `should return profile from local`() = runBlockingTest {
        val result = runCatching { localRepo.getProfile() }

        assertTrue(result.isSuccess)
        assertEquals(expectedLocalProfile, result.getOrNull())
    }

    @Test
    fun `should return news from local`() = runBlockingTest {
        val result = runCatching { localRepo.getNews() }

        assertTrue(result.isSuccess)
        assertEquals(1, result.getOrNull()?.size)
        assertEquals(expectedLocalNews, result.getOrNull()?.first())
    }

    @Test
    fun `local profile empty throws NetworkThrowable`() = runBlockingTest {
        val result = runCatching {
            emptyRepo.getProfile()
        }.onFailure {
            assertTrue(it is NetworkThrowable)
        }

        assertTrue(result.isFailure)
    }

    @Test
    fun `local news empty throws NetworkThrowable`() = runBlockingTest {
        val result = runCatching {
            emptyRepo.getNews()
        }.onFailure {
            assertTrue(it is NetworkThrowable)
        }

        assertTrue(result.isFailure)
    }

    @Test
    fun `Profile unauthenticated throws NetworkThrowable with code 401`() = runBlockingTest {
        val result = runCatching {
            errorRepo.getProfile()
        }.onFailure {
            assertTrue(it is NetworkThrowable)
            assertEquals(ErrorCode.Code401, (it as NetworkThrowable).errorCode)
        }

        assertTrue(result.isFailure)
    }

    @Test
    fun `news unauthenticated throws NetworkThrowable with code 401`() = runBlockingTest {
        val result = runCatching {
            errorRepo.getNews()
        }.onFailure {
            assertTrue(it is NetworkThrowable)
            assertEquals(ErrorCode.Code401, (it as NetworkThrowable).errorCode)
        }

        assertTrue(result.isFailure)
    }
}