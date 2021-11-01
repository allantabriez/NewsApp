package com.example.newsapp.presentation.home

import androidx.compose.foundation.layout.*
import androidx.compose.material.CircularProgressIndicator
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Scaffold
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.newsapp.R
import com.example.newsapp.domain.model.News
import com.example.newsapp.domain.model.Profile
import com.example.newsapp.presentation.home.composables.LoginDialog
import com.example.newsapp.presentation.home.composables.NewsList
import com.example.newsapp.presentation.home.composables.ProfileAppBar
import com.example.newsapp.utils.ErrorCode
import com.example.newsapp.utils.Resource
import org.koin.androidx.compose.getViewModel

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
fun DisplayError() {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(horizontal = 16.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center,
    ) {
        Text(
            text = stringResource(id = R.string.login_dialog_title),
            style = MaterialTheme.typography.h1.copy(
                fontSize = 18.sp,
                letterSpacing = 0.5.sp,
                fontWeight = FontWeight.Medium
            )
        )
        Text(
            text = stringResource(id = R.string.something_went_wrong),
        )
    }
}
