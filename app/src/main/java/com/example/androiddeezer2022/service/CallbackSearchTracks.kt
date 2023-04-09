package com.example.androiddeezer2022.service

import android.util.Log
import com.google.gson.Gson
import okhttp3.Call
import okhttp3.Callback
import okhttp3.Response
import java.io.IOException


abstract class CallbackSearchTracks: Callback {

    companion object {
        private const val TAG = "CallSearchTracks"
    }

    abstract fun fireOnResponseOk(data: TracksSearchResponse)


    override fun onFailure(call: Call, e: IOException) {
        Log.d(TAG, ">>onFailure", e)
    }

    override fun onResponse(call: Call, response: Response) {
        val responseData = response.body!!.string()
        Log.d(TAG, "onResponseTracks: ${responseData}")

        val gson = Gson()
        val data: TracksSearchResponse = gson.fromJson(
            responseData,
            TracksSearchResponse::class.java
        )

        fireOnResponseOk(data)
    }
}