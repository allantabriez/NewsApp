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
import com.example.newsapp.domain.model.Profile
import com.example.newsapp.presentation.theme.black
import com.example.newsapp.presentation.theme.grey

@ExperimentalCoilApi
@Composable
fun ProfileAppBar(profile: Profile) {
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
                    data = profile.picture,
                    builder = {
                        transformations(CircleCropTransformation())
                        error(grey)
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
                    text = profile.name,
                    style = MaterialTheme.typography.h1.copy(
                        fontSize = 18.sp,
                        fontWeight = FontWeight.W700,
                        letterSpacing = 0.5.sp
                    ),
                    softWrap = true,
                    overflow = TextOverflow.Ellipsis
                )
                Text(
                    text = profile.bio,
                    style = MaterialTheme.typography.body2.copy(fontWeight = FontWeight.W400),
                    softWrap = true,
                    overflow = TextOverflow.Ellipsis
                )
                Text(
                    text = profile.web,
                    style = MaterialTheme.typography.body2.copy(fontWeight = FontWeight.W400),
                    softWrap = true,
                    overflow = TextOverflow.Ellipsis
                )
            }
        }
    }
}