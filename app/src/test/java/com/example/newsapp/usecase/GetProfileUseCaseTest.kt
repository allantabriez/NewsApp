package com.example.newsapp.usecase

import com.example.newsapp.data.DummyData
import com.example.newsapp.data.local.entity.toModel
import com.example.newsapp.domain.repository.MeRepository
import com.example.newsapp.domain.usecase.GetProfileUseCase
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
class GetProfileUseCaseTest {

    private val repository: MeRepository = mock()
    private val useCase = GetProfileUseCase(repository)
    private val expectedProfile = DummyData.PROFILE_ENTITY.toModel()

    @Test
    fun `get profile success returns profile data`() = runBlockingTest {
        Mockito.`when`(repository.getProfile()).thenReturn(DummyData.PROFILE_ENTITY.toModel())

        val result = useCase.invoke()
        assertEquals(expectedProfile, result)
        verify(repository, times(1)).getProfile()
    }

    @Test
    fun `get profile fails throws NetworkThrowable`() = runBlockingTest {
        Mockito.`when`(repository.getProfile())
            .thenAnswer {
                throw NetworkThrowable("Failed to load data", ErrorCode.CodeUnknown)
            }

        val result = runCatching {
            useCase.invoke()
        }.onFailure {
            assertTrue { it is NetworkThrowable }
        }

        assertTrue { result.isFailure }
        verify(repository, times(1)).getProfile()
    }
}