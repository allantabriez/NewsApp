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
import coil.size.Scale
import com.example.newsapp.R
import com.example.newsapp.domain.model.News
import com.example.newsapp.presentation.theme.darkerGrey
import com.example.newsapp.presentation.theme.grey
import com.example.newsapp.utils.DateUtils
import kotlinx.coroutines.Dispatchers

@ExperimentalCoilApi
@Composable
fun NewsCard(news: News) {
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
                    data = news.coverImg,
                    builder = {
                        dispatcher(Dispatchers.IO)
                        crossfade(true)
                        this.scale(Scale.FILL)
                    }
                ),
                contentDescription = stringResource(R.string.news_image),
                modifier = Modifier
                    .aspectRatio(2.1F, matchHeightConstraintsFirst = false)
                    .fillMaxWidth()
            )
            Text(
                text = news.title,
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
                        text = news.views.toString(),
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
                        text = news.comments.toString(),
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
                        text = news.upVotes.toString(),
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
                        text = news.downVotes.toString(),
                        style = MaterialTheme.typography.caption.copy(
                            color = darkerGrey,
                            fontWeight = FontWeight.W600,
                        ),
                        modifier = Modifier.padding(start = 4.dp, end = 12.dp)
                    )
                }
                Text(
                    text = DateUtils.toNewsDate(news.date),
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