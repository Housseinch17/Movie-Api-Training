package com.example.movieapiapptraining.ui.di

import com.example.movieapiapptraining.data.api.MovieApiService
import com.example.movieapiapptraining.ui.util.Constants.BASE_URL
import com.jakewharton.retrofit2.converter.kotlinx.serialization.asConverterFactory
import kotlinx.serialization.json.Json
import okhttp3.MediaType.Companion.toMediaType
import org.koin.dsl.module
import retrofit2.Retrofit

object NetworkModule {
    val networkModule = module {
        single {
            Retrofit.Builder()
                .addConverterFactory(Json.asConverterFactory("application/json".toMediaType()))
                .baseUrl(BASE_URL)
                .build()
        }
        single<MovieApiService> {
            get<Retrofit>().create(MovieApiService::class.java)
        }
    }

}