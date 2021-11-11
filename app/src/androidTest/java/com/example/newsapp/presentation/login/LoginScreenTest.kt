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
        composeTestRule.onNodeWithText("TimeRomanNews.").assertExists()
        composeTestRule.onNodeWithContentDescription("Email Input").assertExists()
        composeTestRule.onNodeWithContentDescription("Password Input").assertExists()
        composeTestRule.onNodeWithText("Login").assertExists()
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
        composeTestRule.onNodeWithContentDescription("Home Screen").assertIsDisplayed()
    }
}

/*
class MyComposeTest {

    @get:Rule
    val composeTestRule = createComposeRule()
    // use createAndroidComposeRule<YourActivity>() if you need access to
    // an activity

    @Test
    fun MyTest() {
        // Start the app
        composeTestRule.setContent {
            MyAppTheme {
                MainScreen(uiState = fakeUiState, /*...*/)
            }
        }

        composeTestRule.onNodeWithText("Continue").performClick()

        composeTestRule.onNodeWithText("Welcome").assertIsDisplayed()
    }
}
* */