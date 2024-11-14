package com.example.movieapiapptraining.ui.di

import com.example.movieapiapptraining.data.api.MovieApiService
import com.example.movieapiapptraining.data.datasource.MovieRepositoryImpl
import com.example.movieapiapptraining.data.datasource.popularMovies.PopularMoviesDataSource
import com.example.movieapiapptraining.data.datasource.popularMovies.PopularMoviesDataSourceImpl
import com.example.movieapiapptraining.domain.MovieRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers
import javax.inject.Named
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {

    @Singleton
    @Provides
    fun providePopularMoviesDataSource(movieApiService: MovieApiService): PopularMoviesDataSource {
        return PopularMoviesDataSourceImpl(movieApiService)
    }

    @Singleton
    @Provides
    fun provideMovieRepository(popularMoviesDataSourceImpl: PopularMoviesDataSourceImpl): MovieRepository {
        return MovieRepositoryImpl(popularMoviesDataSourceImpl)
    }

    @Provides
    @Singleton
    @Named("DispatchersIO")
    fun provideCoroutineDispatchersIO(): CoroutineDispatcher = Dispatchers.IO

}