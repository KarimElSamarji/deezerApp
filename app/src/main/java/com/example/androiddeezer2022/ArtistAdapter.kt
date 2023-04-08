import android.content.ContentValues.TAG
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.core.os.bundleOf
import androidx.navigation.Navigation
import androidx.recyclerview.widget.RecyclerView
import com.example.androiddeezer2022.R
import com.example.androiddeezer2022.service.Artist
import com.squareup.picasso.Picasso

class ArtistAdapter(private val artistsList: List<Artist>) :
    RecyclerView.Adapter<ArtistAdapter.ArtistViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ArtistViewHolder {
        val itemView =
            LayoutInflater.from(parent.context).inflate(R.layout.artist_card, parent, false)
        return ArtistViewHolder(itemView)
    }

    override fun onBindViewHolder(holder: ArtistViewHolder, position: Int) {
        val currentArtist = artistsList[position]

        holder.artistNameTextView.text = currentArtist.name
        holder.artistGenreTextView.text = currentArtist.type
        Picasso.get().load(currentArtist.picture).into(holder.artistImageView)

        holder.artistImageView.setOnClickListener {
            Log.d(TAG,"click on ${currentArtist.name}")
            Navigation.findNavController(holder.itemView).navigate(
                R.id.action_ArtistsFragment_to_AlbumsFragment,
                bundleOf("artist" to currentArtist.id))
        }
    }

    override fun getItemCount() = artistsList.size

    inner class ArtistViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val artistImageView: ImageView = itemView.findViewById(R.id.artistImageView)
        val artistNameTextView: TextView = itemView.findViewById(R.id.artistNameTextView)
        val artistGenreTextView: TextView = itemView.findViewById(R.id.artistGenreTextView)
    }
}
