package com.example.newsapp.presentation.home.composables

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.annotation.ExperimentalCoilApi
import coil.compose.rememberImagePainter
import coil.transform.CircleCropTransformation
import com.example.newsapp.R
import com.example.newsapp.presentation.theme.black

@ExperimentalCoilApi
@Composable
fun ProfileAppBar() {
    Surface(
        color = black,
        shape = RoundedCornerShape(bottomEnd = 24.dp),
        modifier = Modifier.fillMaxWidth()
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
                contentDescription = stringResource(R.string.profile_image),
                modifier = Modifier
                    .requiredSize(80.dp)
                    .padding(start = 16.dp)
            )
            Column(
                modifier = Modifier.padding(start = 24.dp, top = 26.dp, end = 16.dp, bottom = 26.dp)
            ) {
                Text(
                    text = "John Doe",
                    style = MaterialTheme.typography.h1.copy(
                        fontSize = 18.sp,
                        fontWeight = FontWeight.W700,
                    ),
                    softWrap = true,
                    overflow = TextOverflow.Ellipsis
                )
                Text(
                    text = "Maju terus pantang mundur",
                    style = MaterialTheme.typography.body2.copy(fontWeight = FontWeight.W400),
                    softWrap = true,
                    overflow = TextOverflow.Ellipsis
                )
                Text(
                    text = "https://linkedin.com/johndoe",
                    style = MaterialTheme.typography.body2.copy(fontWeight = FontWeight.W400),
                    softWrap = true,
                    overflow = TextOverflow.Ellipsis
                )
            }
        }
    }
}