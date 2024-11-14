package com.example.movieapiapptraining.ui.screens.movieDetails

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.movieapiapptraining.data.model.Result
import com.example.movieapiapptraining.ui.util.MovieImage

@Composable
fun MovieDetailsItem(modifier: Modifier, result: Result) {
    Column(
        modifier = modifier,
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.spacedBy(20.dp)
    ) {
        MovieImage(
            modifier = Modifier
                .fillMaxWidth()
                .height(200.dp)
                .clip(RoundedCornerShape(12.5.dp)),
            imageUrl = result.imageUrl
        )
        Text(
            text = result.title,
            style = MaterialTheme.typography.titleLarge.copy(
                fontSize = 20.sp,
                color = Color.Blue,
            )
        )
        Text(
            modifier = Modifier.fillMaxWidth(),
            text = result.overview,
            style = MaterialTheme.typography.bodyLarge.copy(
                fontSize = 16.sp,
                color = Color.Red
            )
        )
    }
}