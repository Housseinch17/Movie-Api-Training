package com.example.movieapiapptraining.ui.screens.popularMovies

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.movieapiapptraining.data.model.MovieResponse
import com.example.movieapiapptraining.domain.usecase.GetPopularMoviesUseCase
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

class PopularMoviesViewModel(
    private val getPopularMoviesUseCase: GetPopularMoviesUseCase
) : ViewModel() {
    private val _popularMoviesState: MutableStateFlow<PopularMoviesUiState> =
        MutableStateFlow(PopularMoviesUiState())
    val popularMoviesState = _popularMoviesState.asStateFlow()

    init {
        viewModelScope.launch {
            Log.d("MyTag", "PopularMoviesViewModel is created")
            getPopularMoviesApi()
        }
    }

    override fun onCleared() {
        super.onCleared()
        Log.d("MyTag", "PopularMoviesViewModel is destroyed")
    }

    private fun getPopularMoviesApi() {
        viewModelScope.launch {
            _popularMoviesState.update { newState ->
                newState.copy(isRefreshing = true)
            }
            getPopularMoviesUseCase.getPopularMovies(3, "popular").collect { newValue ->
                _popularMoviesState.update { newState ->
                    newState.copy(popularMoviesResponse = newValue, isRefreshing = false)
                }
            }
            Log.d("MyTag", "getPopularMoviesApi() in PopularMoviesViewModel is finished")
        }
    }

    fun refreshData() {
        getPopularMoviesApi()
    }
}


sealed interface PopularMoviesResponse {
    data object Loading : PopularMoviesResponse
    data class Error(val error: String) : PopularMoviesResponse
    data class Success(val movieResponse: MovieResponse) : PopularMoviesResponse
}