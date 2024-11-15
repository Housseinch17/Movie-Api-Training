package com.example.movieapiapptraining.data.datasource.popularMovies

import android.util.Log
import com.example.movieapiapptraining.data.api.MovieApiService
import com.example.movieapiapptraining.data.model.MovieResponse
import com.example.movieapiapptraining.ui.util.Constants.API_KEY
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn

class PopularMoviesDataSourceImpl(
    private val movieApiService: MovieApiService,
    private val coroutineDispatcher: CoroutineDispatcher = Dispatchers.IO,
) : PopularMoviesDataSource {

    private val refreshIntervalMs: Long = 50000
    override suspend fun getPopularMovies(movieId: Int, movieGenre: String): Flow<MovieResponse> =
        flow {
            Log.d("MyTag", "getPopularMovies() in PopularMoviesDataSourceImpl Started")
            while (true) {
                val latestNews = movieApiService.getPopularMovies(
                    movieId = movieId, movieGenre = movieGenre,
                    apiKey = API_KEY
                )
                emit(latestNews)
                Log.d("MyTag", "getPopularMovies() in PopularMoviesDataSourceImpl Started")
                delay(refreshIntervalMs)
            }
        }.flowOn(coroutineDispatcher)
}