package com.example.movieapiapptraining.domain.usecase

import com.example.movieapiapptraining.data.datasource.MovieRepositoryImpl
import com.example.movieapiapptraining.ui.screens.popularMovies.PopularMoviesResponse
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class GetPopularMoviesUseCase @Inject constructor(
    private val movieRepositoryImpl: MovieRepositoryImpl
) {
    suspend fun getPopularMovies(movieId: Int, movieGenre: String): Flow<PopularMoviesResponse> =
        movieRepositoryImpl.getPopularMovies(movieId, movieGenre)
}