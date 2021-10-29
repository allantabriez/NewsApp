package com.example.newsapp.presentation.home

import android.util.Log
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Scaffold
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.annotation.ExperimentalCoilApi
import com.example.newsapp.domain.model.Profile
import com.example.newsapp.presentation.home.composables.NewsCard
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
            if (viewModel.state.value is Resource.Success){
                ProfileAppBar(viewModel.state.value.data?.second as Profile)
            }
        }
    ) {
        LazyColumn(
            modifier = Modifier.fillMaxWidth(),
            contentPadding = PaddingValues(
                start = 16.dp, end = 16.dp, top = 28.dp, bottom = 16.dp
            ),
        ) {
            item {
                Text(
                    text = "Hot Topics",
                    style = MaterialTheme.typography.body1.copy(
                        fontSize = 18.sp,
                        fontWeight = FontWeight.W700,
                    )
                )
            }
            item {
                Text(
                    text = "See What's Happening in the World"
                )
            }
            items(listOf(1, 2, 3, 4, 5, 6)) {
                NewsCard()
            }
        }
    }
}