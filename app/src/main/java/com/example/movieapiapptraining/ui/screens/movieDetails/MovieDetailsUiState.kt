package com.example.movieapiapptraining.ui.screens.movieDetails

import androidx.compose.runtime.Immutable

@Immutable
data class MovieDetailsUiState(
    val movieDetailsResponse: MovieDetailsResponse = MovieDetailsResponse.Loading,
)
