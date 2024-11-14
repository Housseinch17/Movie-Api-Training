package com.example.movieapiapptraining.data.model

import kotlinx.serialization.Serializable

@Serializable
data class MovieResponse(
    val page: Int = 0,
    val results: List<Result> = emptyList(),
    val total_pages: Int = 0,
    val total_results: Int = 0
)