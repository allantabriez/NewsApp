package com.example.newsapp.usecase

import com.example.newsapp.data.DummyData
import com.example.newsapp.data.local.entity.toModel
import com.example.newsapp.domain.repository.MeRepository
import com.example.newsapp.domain.usecase.GetNewsUseCase
import com.example.newsapp.utils.ErrorCode
import com.example.newsapp.utils.NetworkThrowable
import com.nhaarman.mockitokotlin2.mock
import com.nhaarman.mockitokotlin2.times
import com.nhaarman.mockitokotlin2.verify
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.runBlockingTest
import org.junit.Assert.*
import org.junit.Test
import org.junit.runner.RunWith
import org.junit.runners.JUnit4
import org.mockito.Mockito
import kotlin.test.assertTrue

@ExperimentalCoroutinesApi
@RunWith(JUnit4::class)
class GetNewsUseCaseTest {

    private val repository: MeRepository = mock()
    private val useCase = GetNewsUseCase(repository)
    private val expectedNews = DummyData.NEWS_ENTITY.toModel()

    @Test
    fun `get news success returns list of news`() = runBlockingTest {
        Mockito.`when`(repository.getNews()).thenReturn(listOf(DummyData.NEWS_ENTITY.toModel()))

        val result = useCase.invoke()
        assertEquals(1, result.size)
        assertEquals(expectedNews, result.first())
        verify(repository, times(1)).getNews()
    }

    @Test
    fun `gte news fails throws NetworkThrowable`() = runBlockingTest {
        Mockito.`when`(repository.getNews())
            .thenAnswer {
                throw NetworkThrowable("Failed to load data", ErrorCode.CodeUnknown)
            }

        val result = runCatching {
            useCase.invoke()
        }.onFailure {
            assertTrue { it is NetworkThrowable }
        }

        assertTrue { result.isFailure }
        verify(repository, times(1)).getNews()
    }
}