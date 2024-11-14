package com.example.movieapiapptraining.domain

import com.example.movieapiapptraining.ui.screens.popularMovies.PopularMoviesResponse
import kotlinx.coroutines.flow.Flow

interface MovieRepository {
    suspend fun getPopularMovies(movieId: Int, movieGenre: String): Flow<PopularMoviesResponse>
}