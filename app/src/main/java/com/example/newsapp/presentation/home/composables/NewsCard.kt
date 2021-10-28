package com.example.newsapp.presentation.home.composables

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.material.Card
import androidx.compose.material.Icon
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import coil.annotation.ExperimentalCoilApi
import coil.compose.rememberImagePainter
import com.example.newsapp.R
import com.example.newsapp.presentation.theme.darkerGrey
import com.example.newsapp.presentation.theme.grey

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
                contentDescription = stringResource(R.string.news_image),
                modifier = Modifier
                    .fillMaxWidth()
                    .aspectRatio(2.1F)
            )
            Text(
                text = "Japan Declares Virus State of Emergency in Tokyo Region",
                maxLines = 2,
                softWrap = true,
                overflow = TextOverflow.Ellipsis,
                modifier = Modifier.padding(top = 4.dp, start = 12.dp, end = 12.dp),
                style = MaterialTheme.typography.body2.copy(fontWeight = FontWeight.W700)
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
                        contentDescription = stringResource(R.string.views),
                        tint = grey,
                        modifier = Modifier.size(16.dp)
                    )
                    Text(
                        text = "5",
                        style = MaterialTheme.typography.caption.copy(
                            color = darkerGrey,
                            fontWeight = FontWeight.W600,
                        ),
                        modifier = Modifier.padding(start = 4.dp, end = 12.dp)
                    )
                    Icon(
                        painter = painterResource(id = R.drawable.icon_comments),
                        contentDescription = stringResource(R.string.comments),
                        tint = grey,
                        modifier = Modifier.size(16.dp)
                    )
                    Text(
                        text = "5",
                        style = MaterialTheme.typography.caption.copy(
                            color = darkerGrey,
                            fontWeight = FontWeight.W600,
                        ),
                        modifier = Modifier.padding(start = 4.dp, end = 12.dp)
                    )
                    Icon(
                        painter = painterResource(id = R.drawable.icon_like),
                        contentDescription = stringResource(R.string.likes),
                        tint = grey,
                        modifier = Modifier.size(16.dp)
                    )
                    Text(
                        text = "5",
                        style = MaterialTheme.typography.caption.copy(
                            color = darkerGrey,
                            fontWeight = FontWeight.W600,
                        ),
                        modifier = Modifier.padding(start = 4.dp, end = 12.dp)
                    )
                    Icon(
                        painter = painterResource(id = R.drawable.icon_dislike),
                        contentDescription = stringResource(R.string.dislikes),
                        tint = grey,
                        modifier = Modifier.size(16.dp)
                    )
                    Text(
                        text = "5",
                        style = MaterialTheme.typography.caption.copy(
                            color = darkerGrey,
                            fontWeight = FontWeight.W600,
                        ),
                        modifier = Modifier.padding(start = 4.dp, end = 12.dp)
                    )
                }
                Text(
                    text = "7 Jan 21",
                    style = MaterialTheme.typography.caption.copy(
                        color = darkerGrey,
                        fontWeight = FontWeight.W600,
                    ),
                    modifier = Modifier.padding(start = 4.dp, end = 12.dp)
                )
            }
        }
    }
}