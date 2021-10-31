package com.example.newsapp.presentation

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.newsapp.presentation.home.HomeScreen
import com.example.newsapp.presentation.login.LoginScreen
import com.example.newsapp.presentation.navigation.NavDestinations.Home
import com.example.newsapp.presentation.navigation.NavDestinations.Login
import com.example.newsapp.presentation.start.StartViewModel
import com.example.newsapp.presentation.theme.NewsAppTheme
import org.koin.androidx.compose.getViewModel

@ExperimentalComposeUiApi
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            NewsApp()
        }
    }
}

@ExperimentalComposeUiApi
@Composable
fun NewsApp() {
    val viewModel: StartViewModel = getViewModel()
    NewsAppTheme {
        val navController = rememberNavController()
        Surface(color = MaterialTheme.colors.background) {
            NewsAppNavHost(
                navController = navController,
                startDestination = if (viewModel.isLoggedIn()) Home.name else Login.name
            )
        }
    }
}

@ExperimentalComposeUiApi
@Composable
fun NewsAppNavHost(navController: NavHostController, startDestination: String) {
    NavHost(
        navController = navController, startDestination = startDestination,
    ) {
        composable(Login.name) {
            LoginScreen {
                navController.navigate(Home.name) {
                    popUpTo(Login.name) { inclusive = true }
                }
            }
        }
        composable(Home.name) {
            HomeScreen {
                navController.navigate(Login.name) {
                    popUpTo(Home.name) { inclusive = true }
                }
            }
        }
    }
}

@ExperimentalComposeUiApi
@Preview(showBackground = true)
@Composable
fun DefaultPreview() {
    NewsAppTheme {
        NewsApp()
    }
}