package com.example.newsapp.presentation.login

import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.test.*
import androidx.compose.ui.test.junit4.createAndroidComposeRule
import androidx.navigation.compose.rememberNavController
import androidx.test.espresso.IdlingRegistry
import com.example.newsapp.presentation.MainActivity
import com.example.newsapp.presentation.NewsAppNavHost
import com.example.newsapp.presentation.navigation.NavDestinations
import com.example.newsapp.presentation.theme.NewsAppTheme
import com.example.newsapp.utils.EspressoIdlingResource
import org.junit.After
import org.junit.Before
import org.junit.Rule
import org.junit.Test

@ExperimentalComposeUiApi
class LoginScreenTest {

    @get:Rule
    val composeTestRule = createAndroidComposeRule<MainActivity>()

    @Before
    fun setup() {
        IdlingRegistry.getInstance().register(EspressoIdlingResource.getEspressoIdlingResource())
        composeTestRule.setContent {
            val controller = rememberNavController()
            NewsAppTheme {
                NewsAppNavHost(
                    navController = controller,
                    startDestination = NavDestinations.Login.name
                )
            }
        }
    }

    @After
    fun tearDown() {
        IdlingRegistry.getInstance().unregister(EspressoIdlingResource.getEspressoIdlingResource())
    }

    @Test
    fun checkUIOnly() {
        composeTestRule.onNodeWithText("TimeRomanNews.").assertIsDisplayed()
        composeTestRule.onNodeWithContentDescription("Email Input").assertIsDisplayed()
        composeTestRule.onNodeWithContentDescription("Password Input").assertIsDisplayed()
        composeTestRule.onNodeWithText("Login").assertIsDisplayed()
    }

    @Test
    fun checkLoginError() {
        composeTestRule.onNodeWithContentDescription("Email Input").performTextInput("tester")
        composeTestRule.onNodeWithContentDescription("Password Input").performTextInput("tester")
        composeTestRule.onNodeWithText("Login").performClick()
        composeTestRule.onNodeWithContentDescription("Error Dialog").assertIsDisplayed()
    }

    @Test
    fun checkLoginSuccess() {
        composeTestRule.onNodeWithContentDescription("Email Input").performTextInput("tester")
        composeTestRule.onNodeWithContentDescription("Password Input").performTextInput("tester123")
        composeTestRule.onNodeWithText("Login").performClick()
        composeTestRule.onNodeWithContentDescription("Home Screen").assertExists()
    }
}