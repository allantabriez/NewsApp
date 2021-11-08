package com.example.newsapp.presentation.login

import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.test.junit4.createAndroidComposeRule
import androidx.compose.ui.test.onNodeWithContentDescription
import androidx.compose.ui.test.onNodeWithText
import androidx.compose.ui.test.performClick
import androidx.compose.ui.test.performTextInput
import com.example.newsapp.presentation.MainActivity
import com.example.newsapp.presentation.theme.NewsAppTheme
import org.junit.Before
import org.junit.Rule
import org.junit.Test

class LoginScreenTest {

    @ExperimentalComposeUiApi
    @get:Rule
    val composeTestRule = createAndroidComposeRule<MainActivity>()

    @ExperimentalComposeUiApi
    @Before
    fun setup() {
        composeTestRule.setContent {
            NewsAppTheme {
                LoginScreen()
            }
        }
    }

    @ExperimentalComposeUiApi
    @Test
    fun checkUI() {
        composeTestRule.onNodeWithText("TimeRomanNews.").assertExists()
        composeTestRule.onNodeWithContentDescription("Email Input").assertExists()
        composeTestRule.onNodeWithContentDescription("Password Input").assertExists()
        composeTestRule.onNodeWithText("Login").assertExists()
    }

    @ExperimentalComposeUiApi
    @Test
    fun checkInput() {
        composeTestRule.onNodeWithContentDescription("Email Input").performTextInput("tester")
        composeTestRule.onNodeWithContentDescription("Password Input").performTextInput("tester123")
        composeTestRule.onNodeWithText("Login").performClick()
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