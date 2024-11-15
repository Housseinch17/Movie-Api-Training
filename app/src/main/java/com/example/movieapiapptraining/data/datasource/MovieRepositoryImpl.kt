package com.example.movieapiapptraining.data.datasource

import android.util.Log
import com.example.movieapiapptraining.data.datasource.popularMovies.PopularMoviesDataSource
import com.example.movieapiapptraining.data.datasource.popularMovies.PopularMoviesDataSourceImpl
import com.example.movieapiapptraining.domain.MovieRepository
import com.example.movieapiapptraining.ui.screens.popularMovies.PopularMoviesResponse
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import retrofit2.HttpException

class MovieRepositoryImpl(
    private val popularMoviesDataSourceImpl: PopularMoviesDataSourceImpl,
) : MovieRepository {

    override suspend fun getPopularMovies(
        movieId: Int,
        movieGenre: String
    ): Flow<PopularMoviesResponse> =
        flow {
            Log.d("MyTag", "getPopularMovies() in MovieRepositoryImpl Started")
            emit(PopularMoviesResponse.Loading)
            try {
                popularMoviesDataSourceImpl.getPopularMovies(movieId, movieGenre)
                    .collect { movieResponse ->
                        emit(PopularMoviesResponse.Success(movieResponse))
                    }
            } catch (e: Exception) {
                emit(PopularMoviesResponse.Error(e.message.toString()))
            } catch (e: HttpException) {
                emit(PopularMoviesResponse.Error(e.message.toString()))
            }
            Log.d("MyTag", "getPopularMovies() in MovieRepositoryImpl Finished")
        }
}