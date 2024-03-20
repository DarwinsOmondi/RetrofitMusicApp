package com.example.music

import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.EditText
import android.widget.ImageView
import android.widget.ListView
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class MainActivity : AppCompatActivity() {
    private lateinit var imageAlbumCover: ImageView
    private lateinit var textAlbumTitle: TextView
    private lateinit var textArtistName: TextView
    private lateinit var listSongs: ListView
    private lateinit var nameEditText: EditText
    private lateinit var searchButton: Button
    private lateinit var musicAdapter: MusicAdapter
    private lateinit var recyclerView: RecyclerView


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        imageAlbumCover = findViewById(R.id.image_album_cover)
        textAlbumTitle = findViewById(R.id.text_album_title)
        textArtistName = findViewById(R.id.text_artist_name)
        nameEditText = findViewById(R.id.nameEditText)
        searchButton = findViewById(R.id.searchButton)
        musicAdapter = MusicAdapter()
        recyclerView = findViewById(R.id.recyclerView)
        recyclerView.adapter = musicAdapter
        recyclerView.layoutManager = LinearLayoutManager(this)

        searchButton.setOnClickListener {
            val name = nameEditText.text.toString()
            nameEditText.text.clear()
            if (name.isNotEmpty()){
                fetchData(name)
            }else{
                nameEditText.hint = "please insert an invalid name "
            }
        }
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
                            Glide.with(this@MainActivity)
                                .load(song.album.cover_medium)
                                .into(imageAlbumCover)

                            textAlbumTitle.text = song.album.title
                            textArtistName.text = song.artist.name

                            musicAdapter.setData(music.data)
                        }
                    }
                } else {
                    Log.e("MainActivity", "Error: ${response.code()}")
                }
            }

            override fun onFailure(call: Call<Music?>, t: Throwable) {
                Log.e("MainActivity", "Error: ${t.message}", t)
            }
        })
    }
}
