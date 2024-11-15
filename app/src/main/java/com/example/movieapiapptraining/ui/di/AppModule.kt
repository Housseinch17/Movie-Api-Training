package com.example.movieapiapptraining.ui.di

import com.example.movieapiapptraining.data.datasource.MovieRepositoryImpl
import com.example.movieapiapptraining.data.datasource.popularMovies.PopularMoviesDataSource
import com.example.movieapiapptraining.data.datasource.popularMovies.PopularMoviesDataSourceImpl
import com.example.movieapiapptraining.domain.MovieRepository
import com.example.movieapiapptraining.domain.usecase.GetPopularMoviesUseCase
import com.example.movieapiapptraining.ui.screens.movieDetails.MovieDetailsViewModel
import com.example.movieapiapptraining.ui.screens.popularMovies.PopularMoviesViewModel
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.core.qualifier.named
import org.koin.dsl.bind
import org.koin.dsl.module

object AppModule {

    private val architectureModule = module {
        //here im injecting PopularMoviesDataSourceImpl
        //and im binding it to PopularMoviesDataSource
        //instead of injecting one for PopularMoviesDataSource like i've done down for MovieRepository
        //and MovieRepositoryImpl
        single<PopularMoviesDataSourceImpl> {
            PopularMoviesDataSourceImpl(
                movieApiService = get(),
                coroutineDispatcher = get(named("DispatchersIO"))
            )
        }bind PopularMoviesDataSource::class
        //inject MovieRepository
        single<MovieRepository> { MovieRepositoryImpl(get()) }
        //inject MovieRepositoryImpl
        single<MovieRepositoryImpl> {
            MovieRepositoryImpl(get())
        }
        single<CoroutineDispatcher>(named("DispatchersIO")) { Dispatchers.IO }
        single { GetPopularMoviesUseCase(get()) }
    }

    private val viewModelModule = module {
        viewModel {
            MovieDetailsViewModel()
        }
        viewModel {
            PopularMoviesViewModel(get())
        }
    }
    val appModule = listOf(NetworkModule.networkModule, architectureModule, viewModelModule)
}