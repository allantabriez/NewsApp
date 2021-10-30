package com.example.newsapp.presentation.home

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.CircularProgressIndicator
import androidx.compose.material.Scaffold
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import coil.annotation.ExperimentalCoilApi
import com.example.newsapp.domain.model.News
import com.example.newsapp.domain.model.Profile
import com.example.newsapp.presentation.home.composables.LoginDialog
import com.example.newsapp.presentation.home.composables.NewsList
import com.example.newsapp.presentation.home.composables.ProfileAppBar
import com.example.newsapp.utils.ErrorCode
import com.example.newsapp.utils.Resource
import org.koin.androidx.compose.getViewModel

@ExperimentalCoilApi
@Composable
fun HomeScreen(
    onLogout: () -> Unit
) {
    val viewModel: HomeViewModel = getViewModel()
    val isDialogOpen = remember { mutableStateOf(false) }

    Scaffold(
        topBar = {
            if (viewModel.state.value is Resource.Success) {
                ProfileAppBar(viewModel.state.value.data?.second as Profile)
            }
        }
    ) {
        when (viewModel.state.value) {
            is Resource.Success -> NewsList(viewModel.state.value.data?.first as List<News>)
            is Resource.Error -> {
                with(viewModel.state.value.message) {
                    if (this == ErrorCode.Code401) {
                        if (!isDialogOpen.value) isDialogOpen.value = true
                        if (isDialogOpen.value) LoginDialog(
                            onDismiss = onLogout,
                            onConfirm = onLogout
                        )
                    } else {
                        DisplayError()
                    }
                }
            }
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

@Composable
fun DisplayError(){
    Box(
        modifier = Modifier.fillMaxSize(),
        contentAlignment = Alignment.Center
    ) {
        Text(
            text = "Sorry, something went wrong.. Please try again later",
        )
    }
}
