package com.example.music

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.navArgs
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.music.RoomDataBase.SearchData
import com.example.music.RoomDataBase.SearchViewModel
import com.example.music.databinding.FragmentHomeBinding
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class HomeFragment : Fragment() {
    private lateinit var binding: FragmentHomeBinding
    private lateinit var imageAlbumCover: ImageView
    private lateinit var textAlbumTitle: TextView
    private lateinit var textArtistName: TextView
    private lateinit var musicAdapter: MusicAdapter
    private lateinit var recyclerView: RecyclerView
    private lateinit var searchViewModel:SearchViewModel
    private lateinit var searchAdapter: SearchAdapter

    private val args: HomeFragmentArgs by navArgs()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = FragmentHomeBinding.inflate(inflater, container, false)

        musicAdapter = MusicAdapter()
        recyclerView = binding.recyclerView
        recyclerView.adapter = musicAdapter
        recyclerView.layoutManager = LinearLayoutManager(requireContext())

        imageAlbumCover = binding.imageAlbumCover
        textAlbumTitle = binding.textAlbumTitle
        textArtistName = binding.textArtistName

        searchAdapter = SearchAdapter()
        searchViewModel = ViewModelProvider(this)[SearchViewModel::class.java]

        val artistName = args.name
        if (artistName.isEmpty()) {
            searchViewModel.readAllSearchData.observe(viewLifecycleOwner){
                searchArtists->
                for (searchArtist in searchArtists){
                    fetchData(searchArtist.artist)
                }
            }
        } else {
            fetchData(artistName)
        }
        return binding.root
    }

    private fun fetchData(artistName: String) {
        val retrofitBuilder = RetrofitClient.getData()
        val retrofitData = retrofitBuilder.getData(artistName)

        retrofitData.enqueue(object : Callback<Music?> {
            override fun onResponse(call: Call<Music?>, response: Response<Music?>) {
                if (response.isSuccessful) {
                    response.body()?.let { music ->
                        val firstSong = music.data.firstOrNull()
                        firstSong?.let { song ->
                          Glide.with(requireContext())
                                .load(song.album.cover_medium)
                                .into(imageAlbumCover)

                            textAlbumTitle.text = song.album.title
                            textArtistName.text = song.artist.name
                            musicAdapter.setData(music.data)
                        }
                    }
                }
            }

            override fun onFailure(call: Call<Music?>, t: Throwable) {
                println("hello")
            }
        })
    }
}
