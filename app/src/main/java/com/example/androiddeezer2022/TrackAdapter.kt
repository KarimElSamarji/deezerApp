package com.example.androiddeezer2022

import android.content.ContentValues
import android.media.AudioAttributes
import android.media.MediaPlayer
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.androiddeezer2022.service.Track
import com.squareup.picasso.Picasso

class TrackAdapter(private val trackList: List<Track>) :
    RecyclerView.Adapter<TrackAdapter.TrackViewHolder>() {

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): TrackAdapter.TrackViewHolder {
        val itemView =
            LayoutInflater.from(parent.context).inflate(R.layout.fragment_track_card, parent, false)
        return TrackViewHolder(itemView)
    }

    override fun onBindViewHolder(holder: TrackAdapter.TrackViewHolder, position: Int) {
        val currentTrack = trackList[position]

        holder.trackTitleTextView.text = currentTrack.title
        holder.trackTypeTextView.text = currentTrack.type

        Log.d("TrackAdapter", "currentTrack.preview: ${currentTrack.md5_image}")


        holder.trackCoverView.setOnClickListener {
            Log.d(ContentValues.TAG, "click on ${currentTrack.title}")

            val mediaPlayer = MediaPlayer().apply {
                setAudioAttributes(
                    AudioAttributes.Builder()
                        .setContentType(AudioAttributes.CONTENT_TYPE_MUSIC)
                        .setUsage(AudioAttributes.USAGE_MEDIA)
                        .build()
                )
                Log.d("TrackAdapter", "currentTrack.link: ${currentTrack.link}")
                setDataSource(currentTrack.preview)
                prepare() // might take long! (for buffering, etc)
                start()
            }
        }
    }

    override fun getItemCount() = trackList.size

    inner class TrackViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val trackCoverView: ImageView = itemView.findViewById(R.id.trackCoverView)
        val trackTitleTextView: TextView = itemView.findViewById(R.id.trackTitleTextView)
        val trackTypeTextView: TextView = itemView.findViewById(R.id.trackTypeTextView)
    }
}
