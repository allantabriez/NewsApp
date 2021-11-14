package com.example.newsapp.presentation.home.composables

import androidx.compose.foundation.layout.*
import androidx.compose.material.Card
import androidx.compose.material.Icon
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.semantics.contentDescription
import androidx.compose.ui.semantics.semantics
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import com.bumptech.glide.load.engine.DiskCacheStrategy
import com.bumptech.glide.request.RequestOptions
import com.example.newsapp.R
import com.example.newsapp.domain.model.News
import com.example.newsapp.presentation.theme.darkerGrey
import com.example.newsapp.presentation.theme.grey
import com.example.newsapp.utils.toNewsDate
import com.skydoves.landscapist.glide.GlideImage

@Composable
fun NewsCard(news: News) {
    val cardDesc = stringResource(R.string.content_news_card)
    val titleDesc = stringResource(R.string.content_news_title)
    val actionDesc = stringResource(R.string.content_news_actions)
    val dateDesc = stringResource(R.string.content_news_date)

    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(top = 16.dp)
            .semantics {
                contentDescription = cardDesc
            },
        elevation = 4.dp
    ) {
        Column(
            modifier = Modifier.fillMaxWidth()
        ) {
            GlideImage(
                imageModel = news.coverImg,
                requestOptions = RequestOptions()
                    .diskCacheStrategy(DiskCacheStrategy.ALL),
                contentScale = ContentScale.FillBounds,
                modifier = Modifier
                    .aspectRatio(2.1F, matchHeightConstraintsFirst = false)
                    .fillMaxWidth(),
                alignment = Alignment.Center,
                contentDescription = stringResource(id = R.string.news_image)
            )
            Text(
                text = news.title,
                maxLines = 2,
                softWrap = true,
                overflow = TextOverflow.Ellipsis,
                modifier = Modifier
                    .padding(top = 4.dp, start = 12.dp, end = 12.dp)
                    .semantics {
                        contentDescription = titleDesc
                    },
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
                    modifier = Modifier.semantics {
                        contentDescription = actionDesc
                    }
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
                    text = news.date.toNewsDate(),
                    style = MaterialTheme.typography.caption.copy(
                        color = darkerGrey,
                        fontWeight = FontWeight.W600,
                    ),
                    modifier = Modifier
                        .padding(start = 4.dp, end = 12.dp)
                        .semantics {
                            contentDescription = dateDesc
                        }
                )
            }
        }
    }
}