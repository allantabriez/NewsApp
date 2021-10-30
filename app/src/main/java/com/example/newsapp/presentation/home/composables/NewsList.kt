package com.example.newsapp.presentation.home.composables

import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.annotation.ExperimentalCoilApi
import com.example.newsapp.domain.model.News

@ExperimentalCoilApi
@Composable
fun NewsList(newsList: List<News>) {
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
        items(items = newsList) {
            NewsCard(news = it)
        }
    }
}