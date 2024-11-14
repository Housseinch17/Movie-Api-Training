package com.example.movieapiapptraining.data.api

import com.example.movieapiapptraining.data.model.MovieResponse
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface MovieApiService {
    @GET("{movie_id}/movie/{movie_genre}")
    suspend fun getPopularMovies(
        @Path("movie_id")movieId: Int,
        @Path("movie_genre")movieGenre: String,
        @Query("api_key")apiKey: String
    ): MovieResponse
}