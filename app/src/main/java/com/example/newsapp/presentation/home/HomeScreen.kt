package com.example.newsapp.presentation.home

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.CircularProgressIndicator
import androidx.compose.material.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import coil.annotation.ExperimentalCoilApi
import com.example.newsapp.domain.model.News
import com.example.newsapp.domain.model.Profile
import com.example.newsapp.presentation.home.composables.NewsList
import com.example.newsapp.presentation.home.composables.ProfileAppBar
import com.example.newsapp.utils.Resource
import org.koin.androidx.compose.getViewModel

@ExperimentalCoilApi
@Composable
fun HomeScreen(
    onLogout: () -> Unit
) {
    val viewModel: HomeViewModel = getViewModel()

    Scaffold(
        topBar = {
            if (viewModel.state.value is Resource.Success) {
                ProfileAppBar(viewModel.state.value.data?.second as Profile)
            }
        }
    ) {
        when (viewModel.state.value) {
            is Resource.Success -> NewsList(viewModel.state.value.data?.first as List<News>)
            is Resource.Error -> {}
            else -> HandleLoading()
        }
    }
}

@Composable
fun HandleLoading() {
    Box(
        modifier = Modifier.fillMaxSize(),
        contentAlignment = Alignment.Center
    ) {
        CircularProgressIndicator()
    }
}