package com.example.newsapp.presentation

import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.test.*
import androidx.compose.ui.test.junit4.createAndroidComposeRule
import androidx.navigation.compose.rememberNavController
import androidx.test.espresso.IdlingRegistry
import androidx.test.internal.runner.junit4.AndroidJUnit4ClassRunner
import com.example.newsapp.R
import com.example.newsapp.presentation.navigation.NavDestinations
import com.example.newsapp.presentation.theme.NewsAppTheme
import com.example.newsapp.utils.EspressoIdlingResource
import org.junit.After
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith

@ExperimentalComposeUiApi
@RunWith(AndroidJUnit4ClassRunner::class)
class HomeScreenTest {

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
    fun checkHome() {
        doLogin()
        checkProfile()
        checkNewsList()
    }

    private fun doLogin() {
        composeTestRule.onNodeWithContentDescription(activity.getString(R.string.content_desc_email_input))
            .performTextInput("tester")
        composeTestRule.onNodeWithContentDescription(activity.getString(R.string.content_pass_input))
            .performTextInput("tester123")
        composeTestRule.onNodeWithText(activity.getString(R.string.login)).performClick()
        composeTestRule.onNodeWithContentDescription(activity.getString(R.string.content_home_screen))
            .assertExists()
    }

    private fun checkProfile() {
        composeTestRule.onNodeWithContentDescription(activity.getString(R.string.content_profile_app_bar))
            .assertIsDisplayed()
        composeTestRule.onNodeWithContentDescription(activity.getString(R.string.content_profile_texts))
            .assertIsDisplayed()
        composeTestRule.onNodeWithContentDescription(activity.getString(R.string.content_profile_texts))
            .assertIsDisplayed()
        composeTestRule.onNodeWithContentDescription(activity.getString(R.string.content_profile_app_bar))
            .assertIsDisplayed()
    }

    private fun checkNewsList() {
        composeTestRule.onNodeWithText(activity.getString(R.string.home_title)).assertIsDisplayed()
        composeTestRule.onNodeWithText(activity.getString(R.string.home_description))
            .assertIsDisplayed()
        composeTestRule.onNodeWithContentDescription(activity.getString(R.string.content_news_list))
            .assertIsDisplayed()
        composeTestRule.onAllNodesWithContentDescription(activity.getString(R.string.content_news_card))
            .fetchSemanticsNodes().isNotEmpty()
        composeTestRule.onAllNodesWithContentDescription(activity.getString(R.string.content_news_card))
            .assertAll(
                hasAnyChild(hasContentDescription(activity.getString(R.string.content_news_title)))
            )
        composeTestRule.onAllNodesWithContentDescription(activity.getString(R.string.content_news_card))
            .assertAll(
                hasAnyChild(hasContentDescription(activity.getString(R.string.content_news_actions)))
            )
        composeTestRule.onAllNodesWithContentDescription(activity.getString(R.string.content_news_card))
            .assertAll(
                hasAnyChild(hasContentDescription(activity.getString(R.string.content_news_date)))
            )
    }
}
