package com.example.movieapiapptraining.ui.screens.popularMovies

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.requiredHeight
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Button
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material.pullrefresh.PullRefreshIndicator
import androidx.compose.material.pullrefresh.pullRefresh
import androidx.compose.material.pullrefresh.rememberPullRefreshState
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.movieapiapptraining.data.model.Result
import com.example.movieapiapptraining.ui.util.MovieImage

@OptIn(ExperimentalMaterialApi::class)
@Composable
fun PopularMoviesScreen(
    modifier: Modifier,
    popularMoviesResponse: PopularMoviesResponse,
    isRefreshing: Boolean,
    onRefresh: () -> Unit,
    onClick: (Result) -> Unit
) {
    val pullRefreshState = rememberPullRefreshState(
        refreshing = isRefreshing,
        onRefresh = onRefresh,
    )
    Box(
        modifier = modifier.pullRefresh(pullRefreshState),
        contentAlignment = Alignment.Center
    ) {
        if (isRefreshing) {
            CircularProgressIndicator(modifier = Modifier.size(200.dp))
        } else {
            Column(
                modifier = modifier,
                verticalArrangement = Arrangement.Center,
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                when (popularMoviesResponse) {
                    is PopularMoviesResponse.Error -> {
                        Button(onClick = onRefresh) {
                            Text(popularMoviesResponse.error)
                        }
                    }

                    PopularMoviesResponse.Loading -> {
                        CircularProgressIndicator(
                            modifier = Modifier.size(200.dp),
                        )
                    }

                    is PopularMoviesResponse.Success -> {
                        PopularMoviesList(
                            modifier = modifier,
                            list = popularMoviesResponse.movieResponse.results, onClick = onClick
                        )
                    }
                }
            }
            // Adding the PullRefreshIndicator
            PullRefreshIndicator(
                refreshing = isRefreshing,
                state = pullRefreshState,
                modifier = Modifier.align(Alignment.TopCenter)
            )
        }
    }
}

@Composable
fun PopularMoviesItem(modifier: Modifier, result: Result, onClick: () -> Unit) {
    Row(
        modifier = modifier
            .border(BorderStroke(1.dp, Color.Blue))
            .clip(RoundedCornerShape(12.5.dp))
            .clickable {
                onClick()
            },
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.spacedBy(14.dp)
    ) {
        MovieImage(
            modifier = Modifier
                .width(100.dp)
                .requiredHeight(140.dp)
                .padding(vertical = 10.dp),
            imageUrl = result.imageUrl
        )
        Column(
            modifier = Modifier.weight(1f),
            verticalArrangement = Arrangement.SpaceAround,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Text(
                modifier = Modifier.fillMaxWidth(),
                text = result.title,
                style = MaterialTheme.typography.titleLarge.copy(
                    fontSize = 16.sp,
                    color = Color.Blue
                )
            )
            Text(
                modifier = Modifier.fillMaxWidth(),
                text = result.overview,
                style = MaterialTheme.typography.bodyMedium.copy(
                    fontSize = 12.sp,
                    color = Color.Red
                ),
                maxLines = 3,
                overflow = TextOverflow.Ellipsis
            )
        }
    }
}

@Composable
fun PopularMoviesList(modifier: Modifier, list: List<Result>, onClick: (Result) -> Unit) {
    LazyColumn(
        modifier = modifier,
        horizontalAlignment = Alignment.Start,
        verticalArrangement = Arrangement.spacedBy(12.dp)
    ) {
        items(list) { item ->
            PopularMoviesItem(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(120.dp),
                result = item,
                onClick = {
                    onClick(item)
                }
            )
        }
    }
}