package com.example.movieapiapptraining.ui.util

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import coil.compose.AsyncImage
import coil.request.ImageRequest
import com.example.movieapiapptraining.R
import com.example.movieapiapptraining.ui.util.Constants.IMAGE_URL

@Composable
fun MovieImage(modifier: Modifier,imageUrl: String){
    AsyncImage(
        model = ImageRequest.Builder(LocalContext.current)
            .data(IMAGE_URL+imageUrl).crossfade(true)
            .placeholder(R.drawable.loading)
            .error(R.drawable.connectionerror)
            .build(),
        contentDescription = null,
        contentScale = ContentScale.FillBounds,
        modifier = modifier
    )
}