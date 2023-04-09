package com.example.androiddeezer2022

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.androiddeezer2022.service.*

// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

/**
 * A simple [Fragment] subclass.
 * Use the [AlbumsFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class AlbumsFragment : Fragment() {
    // TODO: Rename and change types of parameters
    private var param1: String? = null
    private var param2: String? = null

    private lateinit var recyclerView: RecyclerView;

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            param1 = it.getString(ARG_PARAM1)
            param2 = it.getString(ARG_PARAM2)
        }

        val artistId = arguments?.getInt("artist")
        Log.d("AlbumsFragment", "artistId: $artistId")

        val deezerService = DeezerService()

        val callback = object : CallbackSearchAlbums() {
            override fun fireOnResponseOk(response: AlbumsSearchResponse) {
                requireActivity().runOnUiThread {
                    recyclerView.adapter = AlbumAdapter(response.data)
                }
            }
        }
        deezerService.searchAlbums(artistId, callback)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_albums, container, false)
    }

    override fun onViewCreated(itemView: View, savedInstanceState: Bundle?) {
        super.onViewCreated(itemView, savedInstanceState)
        recyclerView = itemView.findViewById(R.id.albumsRecyclerView)
        recyclerView.layoutManager = LinearLayoutManager(context)
        recyclerView.setHasFixedSize(true)

    }

    companion object {
        /**
         * Use this factory method to create a new instance of
         * this fragment using the provided parameters.
         *
         * @param param1 Parameter 1.
         * @param param2 Parameter 2.
         * @return A new instance of fragment AlbumsFragment.
         */
        // TODO: Rename and change types and number of parameters
        @JvmStatic
        fun newInstance(param1: String, param2: String) =
            AlbumsFragment().apply {
                arguments = Bundle().apply {
                    putString(ARG_PARAM1, param1)
                    putString(ARG_PARAM2, param2)
                }
            }
    }
}


/*val albumList = listOf(
                        Album(
                            "https://cdns-images.dzcdn.net/images/cover/4b4b8c2fa52a474e0777c77244e05a38/500x500-000000-80-0-0.jpg",
                            "https://cdns-images.dzcdn.net/images/cover/4b4b8c2fa52a474e0777c77244e05a38/1000x1000-000000-80-0-0.jpg",
                            "https://cdns-images.dzcdn.net/images/cover/4b4b8c2fa52a474e0777c77244e05a38/250x250-000000-80-0-0.jpg",
                            "https://cdns-images.dzcdn.net/images/cover/4b4b8c2fa52a474e0777c77244e05a38/56x56-000000-80-0-0.jpg",
                            "https://cdns-images.dzcdn.net/images/cover/4b4b8c2fa52a474e0777c77244e05a38/1000x1000-000000-80-0-0.jpg",
                            false,
                            100,
                            1,
                            1,
                            "https://www.deezer.com/en/album/1",
                            "1c8b06d15f2af3b1c3c35041a938d970",
                            "album",
                            "2022-01-01",
                            "Album 1",
                            "https://api.deezer.com/album/1/tracks",
                            "album"
                        ),
                        Album(
                            "https://cdns-images.dzcdn.net/images/cover/4b4b8c2fa52a474e0777c77244e05a38/500x500-000000-80-0-0.jpg",
                            "https://cdns-images.dzcdn.net/images/cover/4b4b8c2fa52a474e0777c77244e05a38/1000x1000-000000-80-0-0.jpg",
                            "https://cdns-images.dzcdn.net/images/cover/4b4b8c2fa52a474e0777c77244e05a38/250x250-000000-80-0-0.jpg",
                            "https://cdns-images.dzcdn.net/images/cover/4b4b8c2fa52a474e0777c77244e05a38/56x56-000000-80-0-0.jpg",
                            "https://cdns-images.dzcdn.net/images/cover/4b4b8c2fa52a474e0777c77244e05a38/1000x1000-000000-80-0-0.jpg",
                            true,
                            200,
                            2,
                            2,
                            "https://www.deezer.com/en/album/2",
                            "2c8b06d15f2af3b1c3c35041a938d970",
                            "album",
                            "2022-02-01",
                            "Album 2",
                            "https://api.deezer.com/album/2/tracks",
                            "album"
                        ),
                    )*/