package com.example.androiddeezer2022.service

import android.util.Log
import com.google.gson.Gson
import okhttp3.Call
import okhttp3.Callback
import okhttp3.Response
import java.io.IOException

abstract class CallbackSearchArtist : Callback {

    companion object {
        private const val TAG = "CallSearchArtist"
    }

    abstract fun fireOnResponseOk(data: ArtistSearchResponse)


    override fun onFailure(call: Call, e: IOException) {
        Log.d(TAG, ">>onFailure", e)
    }

    override fun onResponse(call: Call, response: Response) {
        val responseData = response.body!!.string()
        Log.d(TAG, "onResponse: ${responseData}")

        val gson = Gson()
        val data: ArtistSearchResponse = gson.fromJson(
            responseData,
            ArtistSearchResponse::class.java
        )

        fireOnResponseOk(data)
    }


}