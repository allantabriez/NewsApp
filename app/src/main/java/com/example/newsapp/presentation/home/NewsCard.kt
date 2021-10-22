package com.example.newsapp.presentation.home

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Card
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import coil.annotation.ExperimentalCoilApi
import coil.compose.rememberImagePainter
import com.example.newsapp.R
import com.example.newsapp.presentation.theme.NewsAppTheme

@ExperimentalCoilApi
@Composable
fun NewsCard(title: String) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(top = 16.dp),
        elevation = 4.dp
    ) {
        Column(
            modifier = Modifier.fillMaxWidth()
        ) {
            Image(
                painter = rememberImagePainter(
                    data = "https://test.com",
                    builder = {
                        placeholder(R.drawable.dummy_news)
                        error(R.drawable.dummy_news)
                    }
                ),
                contentDescription = null,
                modifier = Modifier
                    .fillMaxWidth()
                    .aspectRatio(2.1F)
            )
            Text(
                text = "Japan Declares Virus State of Emergency in Tokyo Region",
                maxLines = 2,
                softWrap = true,
                overflow = TextOverflow.Ellipsis,
                fontWeight = FontWeight.W700,
                modifier = Modifier.padding(top = 4.dp, start = 12.dp, end = 12.dp),
            )
            Column(modifier = Modifier.fillMaxWidth().padding(horizontal = 12.dp)) {

            }
        }
    }
}

@ExperimentalCoilApi
@Preview(showBackground = true)
@Composable
fun NewsCardPreview() {
    NewsAppTheme {
        NewsCard(title = "Test")
    }
}