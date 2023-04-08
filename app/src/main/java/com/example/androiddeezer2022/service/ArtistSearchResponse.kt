package com.example.androiddeezer2022.service

data class ArtistSearchResponse(
    val `data`: List<Artist>,
    val next: String,
    val total: Int
)