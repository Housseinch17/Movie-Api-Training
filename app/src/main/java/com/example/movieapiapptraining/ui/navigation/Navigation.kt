package com.example.movieapiapptraining.ui.navigation

import android.util.Log
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.navigation.NavBackStackEntry
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.navigation
import androidx.navigation.toRoute
import com.example.movieapiapptraining.data.model.Result
import com.example.movieapiapptraining.ui.screens.movieDetails.MovieDetailsItem
import com.example.movieapiapptraining.ui.screens.popularMovies.PopularMoviesScreen
import com.example.movieapiapptraining.ui.screens.popularMovies.PopularMoviesViewModel
import com.example.movieapiapptraining.ui.util.CustomNavType
import kotlinx.serialization.Serializable
import org.koin.androidx.compose.koinViewModel
import kotlin.reflect.typeOf

@Composable
fun Navigation(modifier: Modifier, navController: NavHostController) {
    NavHost(
        modifier = modifier
            .background(Color.Gray)
            .padding(20.dp),
        navController = navController,
        startDestination = MovieScreen.PopularMoviesGraph
    ) {
        navigation<MovieScreen.PopularMoviesGraph>(startDestination = MovieScreen.PopularMovies) {
            composable<MovieScreen.PopularMovies> {
                val parentBackStackEntry: NavBackStackEntry =
                    navController.getBackStackEntry(MovieScreen.PopularMoviesGraph)
                val popularMoviesViewModel =
                    koinViewModel<PopularMoviesViewModel>()

                val popularMoviesUiState by popularMoviesViewModel.popularMoviesState.collectAsStateWithLifecycle()

                PopularMoviesScreen(
                    modifier = modifier,
                    popularMoviesResponse = popularMoviesUiState.popularMoviesResponse,
                    isRefreshing = popularMoviesUiState.isRefreshing,
                    onRefresh = popularMoviesViewModel::refreshData
                ) { item ->
                    navController.navigate(
                        MovieScreen.MovieDetails(
                            result = item
                        )
                    )
                }
            }

            composable<MovieScreen.MovieDetails>(
                typeMap = mapOf(
                    typeOf<Result>() to CustomNavType.movieDetails
                )
            ) { entry ->
                val args = entry.toRoute<MovieScreen.MovieDetails>()
                Log.d("MyTag", "${args.result}")
                MovieDetailsItem(
                    modifier = modifier,
                    result = args.result
                )
            }
        }
    }
}

@Serializable
sealed interface MovieScreen {
    //Navigation Screens
    @Serializable
    data object PopularMovies : MovieScreen

    @Serializable
    data class MovieDetails(val result: Result = Result()) : MovieScreen

    //Navigation Graphs
    @Serializable
    data object PopularMoviesGraph : MovieScreen
}