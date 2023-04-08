package com.example.androiddeezer2022.service

import okhttp3.*

class DeezerService {

    fun searchArtist(name: String?, callback: CallbackSearchArtist) {
        val client = OkHttpClient()
        val request: Request = Request.Builder()
            .url("https://api.deezer.com/search/artist?q=${name}")
            .build()
        client.newCall(request).enqueue(callback)
    }

    fun searchAlbums(id: Int?, callback: CallbackSearchAlbums) {
        val client = OkHttpClient()
        val request: Request = Request.Builder()
            .url("https://api.deezer.com/artist/${id}/albums")
            .build()
        client.newCall(request).enqueue(callback)
    }

}