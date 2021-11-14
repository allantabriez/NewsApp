package com.example.newsapp.presentation

import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.test.*
import androidx.compose.ui.test.junit4.createAndroidComposeRule
import androidx.navigation.compose.rememberNavController
import androidx.test.espresso.IdlingRegistry
import com.example.newsapp.R
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
    private lateinit var activity: MainActivity

    @Before
    fun setup() {
        activity = composeTestRule.activity
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
        composeTestRule.onNodeWithText(activity.getString(R.string.login_title)).assertIsDisplayed()
        composeTestRule.onNodeWithContentDescription(activity.getString(R.string.content_desc_email_input)).assertIsDisplayed()
        composeTestRule.onNodeWithContentDescription(activity.getString(R.string.content_pass_input)).assertIsDisplayed()
        composeTestRule.onNodeWithText(activity.getString(R.string.login)).assertIsDisplayed()
    }

    @Test
    fun checkLoginError() {
        composeTestRule.onNodeWithContentDescription(activity.getString(R.string.content_desc_email_input)).performTextInput("tester")
        composeTestRule.onNodeWithContentDescription(activity.getString(R.string.content_pass_input)).performTextInput("tester")
        composeTestRule.onNodeWithText(activity.getString(R.string.login)).performClick()
        composeTestRule.onNodeWithContentDescription(activity.getString(R.string.content_error_dialog)).assertIsDisplayed()
    }

    @Test
    fun checkLoginSuccess() {
        composeTestRule.onNodeWithContentDescription(activity.getString(R.string.content_desc_email_input)).performTextInput("tester")
        composeTestRule.onNodeWithContentDescription(activity.getString(R.string.content_pass_input)).performTextInput("tester123")
        composeTestRule.onNodeWithText(activity.getString(R.string.login)).performClick()
        composeTestRule.onNodeWithContentDescription(activity.getString(R.string.content_home_screen)).assertExists()
    }
}