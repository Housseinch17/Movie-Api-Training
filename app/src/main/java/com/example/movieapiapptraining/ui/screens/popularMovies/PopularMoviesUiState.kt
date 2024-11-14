package com.example.movieapiapptraining.ui.screens.popularMovies

import androidx.compose.runtime.Immutable

@Immutable
data class PopularMoviesUiState(
    val popularMoviesResponse: PopularMoviesResponse = PopularMoviesResponse.Loading,
    val isRefreshing: Boolean = false,
)
