package com.example.movieapiapptraining.data.datasource.popularMovies

import com.example.movieapiapptraining.data.model.MovieResponse
import kotlinx.coroutines.flow.Flow

interface PopularMoviesDataSource {
    suspend fun getPopularMovies(movieId: Int, movieGenre: String): Flow<MovieResponse>
}