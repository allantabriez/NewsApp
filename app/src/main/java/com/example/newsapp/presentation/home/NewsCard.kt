package com.example.newsapp.presentation.home

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.material.Card
import androidx.compose.material.Icon
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.annotation.ExperimentalCoilApi
import coil.compose.rememberImagePainter
import com.example.newsapp.R
import com.example.newsapp.presentation.theme.NewsAppTheme

@ExperimentalCoilApi
@Composable
fun NewsCard() {
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
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(top = 8.dp, bottom = 16.dp, start = 12.dp, end = 12.dp),
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                Row(
                    verticalAlignment = Alignment.CenterVertically,
                ) {
                    Icon(
                        painter = painterResource(id = R.drawable.icon_view),
                        contentDescription = null,
                        tint = Color(0xFF979797),
                        modifier = Modifier.size(16.dp)
                    )
                    Text(
                        text = "5",
                        color = Color(0xFF888888),
                        fontSize = 12.sp,
                        fontWeight = FontWeight.W600,
                        modifier = Modifier.padding(start = 4.dp, end = 12.dp)
                    )
                    Icon(
                        painter = painterResource(id = R.drawable.icon_comments),
                        contentDescription = null,
                        tint = Color(0xFF979797),
                        modifier = Modifier.size(16.dp)
                    )
                    Text(
                        text = "5",
                        color = Color(0xFF888888),
                        fontSize = 12.sp,
                        fontWeight = FontWeight.W600,
                        modifier = Modifier.padding(start = 4.dp, end = 12.dp)
                    )
                    Icon(
                        painter = painterResource(id = R.drawable.icon_like),
                        contentDescription = null,
                        tint = Color(0xFF979797),
                        modifier = Modifier.size(16.dp)
                    )
                    Text(
                        text = "5",
                        color = Color(0xFF888888),
                        fontSize = 12.sp,
                        fontWeight = FontWeight.W600,
                        modifier = Modifier.padding(start = 4.dp, end = 12.dp)
                    )
                    Icon(
                        painter = painterResource(id = R.drawable.icon_dislike),
                        contentDescription = null,
                        tint = Color(0xFF979797),
                        modifier = Modifier.size(16.dp)
                    )
                    Text(
                        text = "5",
                        color = Color(0xFF888888),
                        fontSize = 12.sp,
                        fontWeight = FontWeight.W600,
                        modifier = Modifier.padding(start = 4.dp, end = 12.dp)
                    )
                }
                Text(
                    text = "7 Jan 21",
                    color = Color(0xFF888888),
                    fontSize = 12.sp,
                    fontWeight = FontWeight.W600,
                    modifier = Modifier.padding(start = 4.dp, end = 12.dp)
                )
            }
        }
    }
}

//@ExperimentalCoilApi
//@Preview(showBackground = true)
//@Composable
//fun NewsCardPreview() {
//    NewsAppTheme {
//        NewsCard()
//    }
//}