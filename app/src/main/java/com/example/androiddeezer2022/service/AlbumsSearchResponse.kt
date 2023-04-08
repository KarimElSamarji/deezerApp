package com.example.androiddeezer2022.service

data class AlbumsSearchResponse(
    val `data`: List<Album>,
    val next: String,
    val total: Int
)