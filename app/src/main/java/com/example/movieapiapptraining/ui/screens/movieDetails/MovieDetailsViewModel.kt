package com.example.movieapiapptraining.ui.screens.movieDetails

import androidx.lifecycle.ViewModel
import com.example.movieapiapptraining.data.model.Result
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow

class MovieDetailsViewModel: ViewModel() {
    private val _movieDetailsUiState: MutableStateFlow<MovieDetailsUiState> = MutableStateFlow(MovieDetailsUiState())
    val movieDetailsUiState: StateFlow<MovieDetailsUiState> = _movieDetailsUiState.asStateFlow()

    init {

    }

}

sealed interface MovieDetailsResponse{
    data object Loading: MovieDetailsResponse
    data class Success(val result: Result): MovieDetailsResponse
}