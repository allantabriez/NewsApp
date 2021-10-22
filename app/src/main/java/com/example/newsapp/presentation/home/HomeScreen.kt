package com.example.newsapp.presentation.home

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Scaffold
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.annotation.ExperimentalCoilApi
import coil.compose.rememberImagePainter
import coil.transform.CircleCropTransformation
import com.example.newsapp.R
import com.example.newsapp.presentation.theme.NewsAppTheme

@ExperimentalCoilApi
@Composable
fun HomeScreen() {
    Scaffold(
        topBar = {
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .background(
                        color = Color.Black,
                        shape = RoundedCornerShape(bottomEnd = 12.dp)
                    )
            ) {
                Row(
                    verticalAlignment = Alignment.CenterVertically,
                ) {
                    Image(
                        painter = rememberImagePainter(
                            data = "https://test.com",
                            builder = {
                                transformations(CircleCropTransformation())
                                placeholder(R.drawable.dummy_profile)
                                error(R.drawable.dummy_profile)
                            }
                        ),
                        contentDescription = null,
                        modifier = Modifier.requiredSize(80.dp).padding(start = 16.dp)
                    )
                    Column(
                        modifier = Modifier.padding(start = 24.dp, top = 26.dp, end = 16.dp, bottom =  26.dp)
                    ) {
                        Text(
                            text = "John Doe",
                            fontSize = 18.sp,
                            fontWeight = FontWeight.W700,
                            color = Color.White
                        )
                        Text(
                            text = "Maju terus pantang mundur",
                            color = Color.White
                        )
                        Text(
                            text = "https://linkedin.com/johndoe",
                            color = Color.White
                        )
                    }
                }
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
                    fontSize = 18.sp,
                    fontWeight = FontWeight.W700,
                )
            }
            item {
                Text(
                    text = "See What's Happening in the World"
                )
            }
            items(listOf(1, 2, 3, 4, 5, 6)) { item ->
                NewsCard(title = item.toString())
            }
        }
    }
}

//@ExperimentalCoilApi
//@Preview(showBackground = true)
//@Composable
//fun HomeScreenPreview() {
//    NewsAppTheme {
//        HomeScreen()
//    }
//}