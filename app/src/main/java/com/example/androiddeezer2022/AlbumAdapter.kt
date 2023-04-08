package com.example.androiddeezer2022

import android.content.ContentValues
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.core.os.bundleOf
import androidx.navigation.Navigation
import androidx.recyclerview.widget.RecyclerView
import com.example.androiddeezer2022.service.Album
import com.squareup.picasso.Picasso

class AlbumAdapter (private val albumList: List<Album>):
    RecyclerView.Adapter<AlbumAdapter.AlbumViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): AlbumViewHolder {
        val itemView =
            LayoutInflater.from(parent.context).inflate(R.layout.fragment_album_card, parent, false)
        return AlbumViewHolder(itemView)
    }

    override fun onBindViewHolder(holder: AlbumViewHolder, position: Int) {
        val currentAlbum = albumList[position]

        holder.albumTitleTextView.text = currentAlbum.title
        holder.albumTypeTextView.text = currentAlbum.type
        Picasso.get().load(currentAlbum.cover).into(holder.albumCoverView)


        holder.albumCoverView.setOnClickListener {
            Log.d(ContentValues.TAG,"click on ${currentAlbum.title}")
            Navigation.findNavController(holder.itemView).navigate(
                R.id.action_AlbumsFragment_to_TracksFragment,
                bundleOf("album" to currentAlbum.id)
            )
        }
    }

    override fun getItemCount() = albumList.size

    inner class AlbumViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val albumCoverView: ImageView = itemView.findViewById(R.id.albumCoverView)
        val albumTitleTextView: TextView = itemView.findViewById(R.id.albumTitleTextView)
        val albumTypeTextView: TextView = itemView.findViewById(R.id.albumTypeTextView)
    }
}