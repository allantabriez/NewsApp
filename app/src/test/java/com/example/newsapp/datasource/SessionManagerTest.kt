package com.example.newsapp.datasource

import android.content.SharedPreferences
import com.example.newsapp.data.local.SessionManager
import com.example.newsapp.modules.loginLocalSourceTestModule
import com.nhaarman.mockitokotlin2.mock
import com.nhaarman.mockitokotlin2.times
import com.nhaarman.mockitokotlin2.verify
import org.junit.Assert.assertEquals
import org.junit.Rule
import org.junit.Test
import org.koin.test.KoinTest
import org.koin.test.KoinTestRule
import org.koin.test.inject
import org.mockito.Mockito

class SessionManagerTest : KoinTest {

    private val sharedPref = mock<SharedPreferences>()
    private val sessionManager: SessionManager by inject()
    private val expectedToken = "token"
    private val expectedDate = "date"

    @get:Rule
    val koinTestRule = KoinTestRule.create {
        printLogger()
        modules(loginLocalSourceTestModule(sharedPref))
    }

    @Test
    fun `save session`() {
        try {
            sessionManager.saveSession("", "")
        } catch (e: Exception) {}
        verify(sharedPref, times(1)).edit()
    }

    @Test
    fun `retrieve token`() {
        Mockito.`when`(sharedPref.getString(SessionManager.TOKEN, "")).thenReturn(expectedToken)
        val token = sessionManager.getToken()
        assertEquals(expectedToken, token)
    }

    @Test
    fun `retrieve expiry date`() {
        Mockito.`when`(sharedPref.getString(SessionManager.EXPIRED_AT, "")).thenReturn(expectedDate)
        val date = sessionManager.getExpiredAt()
        assertEquals(expectedDate, date)
    }

    @Test
    fun `delete session`() {
        try {
            sessionManager.deleteSession()
        } catch (e: Exception) {}

        verify(sharedPref, times(1)).all
    }
}