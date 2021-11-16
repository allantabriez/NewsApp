package com.example.newsapp.datasource

import com.example.newsapp.data.DummyData
import com.example.newsapp.data.local.MeLocalDataSource
import com.example.newsapp.data.local.room.NewsDao
import com.example.newsapp.data.local.room.ProfileDao
import com.example.newsapp.modules.meLocalSourceTestModule
import com.nhaarman.mockitokotlin2.mock
import com.nhaarman.mockitokotlin2.times
import com.nhaarman.mockitokotlin2.verify
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.runBlockingTest
import org.junit.Assert.assertEquals
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.junit.runners.JUnit4
import org.koin.test.KoinTest
import org.koin.test.KoinTestRule
import org.koin.test.inject
import org.mockito.Mockito
import kotlin.test.assertTrue

@ExperimentalCoroutinesApi
@RunWith(JUnit4::class)
class MeLocalDataSourceTest : KoinTest {

    private val newsDao = mock<NewsDao>()
    private val profileDao = mock<ProfileDao>()
    private val dataSource: MeLocalDataSource by inject()
    private val expectedProfile = DummyData.PROFILE_ENTITY
    private val expectedNews = listOf(DummyData.NEWS_ENTITY)

    @get:Rule
    val koinTestRule = KoinTestRule.create {
        printLogger()
        modules(meLocalSourceTestModule(newsDao, profileDao))
    }

    @Test
    fun `insert profile check that profileDao insertProfile is called`() = runBlockingTest {
        dataSource.insertProfile(DummyData.PROFILE_ENTITY)

        verify(profileDao, times(1)).insertProfile(DummyData.PROFILE_ENTITY)
    }

    @Test
    fun `get profile success should return list with 1 expected profile`() = runBlockingTest {
        Mockito.`when`(profileDao.getProfile()).thenReturn(listOf(DummyData.PROFILE_ENTITY))

        val response = dataSource.getCachedProfile()
        assertEquals(expectedProfile, response.first())
    }

    @Test
    fun `delete profile check that profileDao deleteProfile is called`() = runBlockingTest {
        dataSource.deleteCachedProfile()

        verify(profileDao, times(1)).deleteProfile()
    }

    @Test
    fun `get profile empty should return empty list`() = runBlockingTest {
        Mockito.`when`(profileDao.getProfile()).thenReturn(emptyList())

        val response = dataSource.getCachedProfile()
        assertTrue { response.isEmpty() }
    }

    @Test
    fun `insert news check that newsDao insertNews is called`() = runBlockingTest {
        newsDao.insertNews(emptyList())

        verify(newsDao, times(1)).insertNews(emptyList())
    }

    @Test
    fun `get news success should return with expected list of news`() = runBlockingTest {
        Mockito.`when`(newsDao.getNews()).thenReturn(listOf(DummyData.NEWS_ENTITY))

        val response = dataSource.getCachedNews()
        assertEquals(expectedNews.size, response.size)
        assertEquals(expectedNews.first(), response.first())
    }

    @Test
    fun `delete news check that newsDao deleteNews is called`() = runBlockingTest {
        newsDao.deleteNews()

        verify(newsDao, times(1)).deleteNews()
    }

    @Test
    fun `get news empty should return empty list`() = runBlockingTest {
        Mockito.`when`(newsDao.getNews()).thenReturn(emptyList())

        val response = dataSource.getCachedNews()
        assertTrue { response.isEmpty() }
    }
}