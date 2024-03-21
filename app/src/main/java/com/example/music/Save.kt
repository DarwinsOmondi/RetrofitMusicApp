package com.example.music

//package com.example.music
//
//import android.os.Bundle
//import android.util.Log
//import android.widget.Button
//import android.widget.EditText
//import android.widget.ImageView
//import android.widget.TextView
//import androidx.appcompat.app.AppCompatActivity
//import androidx.recyclerview.widget.LinearLayoutManager
//import androidx.recyclerview.widget.RecyclerView
//import com.bumptech.glide.Glide
//import retrofit2.Call
//import retrofit2.Callback
//import retrofit2.Response
//
//
//class MainActivity : AppCompatActivity() {
//    private lateinit var imageAlbumCover: ImageView
//    private lateinit var textAlbumTitle: TextView
//    private lateinit var textArtistName: TextView
//    private lateinit var nameEditText: EditText
//    private lateinit var searchButton: Button
//    private lateinit var musicAdapter: MusicAdapter
//    private lateinit var recyclerView: RecyclerView
//
//    override fun onCreate(savedInstanceState: Bundle?) {
//        super.onCreate(savedInstanceState)
//        setContentView(R.layout.activity_main)
//
//        imageAlbumCover = findViewById(R.id.image_album_cover)
//        textAlbumTitle = findViewById(R.id.text_album_title)
//        textArtistName = findViewById(R.id.text_artist_name)
//        nameEditText = findViewById(R.id.nameEditText)
//        searchButton = findViewById(R.id.searchButton)
//        musicAdapter = MusicAdapter()
//        recyclerView = findViewById(R.id.recyclerView)
//        recyclerView.adapter = musicAdapter
//        recyclerView.layoutManager = LinearLayoutManager(this)
//
//        searchButton.setOnClickListener {
//            val name = nameEditText.text.toString()
//            nameEditText.text.clear()
//            if (name.isNotEmpty()){
//                fetchData(name)
//            }else{
//                nameEditText.hint = "please insert an invalid name "
//            }
//        }
//    }
//    private fun fetchData(artistName: String) {
//
//        val retrofitBuilder = RetrofitClient.getData()
//        val retrofitData = retrofitBuilder.getData(artistName)
//
//        retrofitData.enqueue(object : Callback<Music?> {
//            override fun onResponse(call: Call<Music?>, response: Response<Music?>) {
//                if (response.isSuccessful) {
//                    response.body()?.let { music ->
//                        val firstSong = music.data.firstOrNull()
//                        firstSong?.let { song ->
//                            Glide.with(this@MainActivity)
//                                .load(song.album.cover_medium)
//                                .into(imageAlbumCover)
//
//                            textAlbumTitle.text = song.album.title
//                            textArtistName.text = song.artist.name
//
//                            musicAdapter.setData(music.data)
//                        }
//                    }
//                }
//            }
//            override fun onFailure(call: Call<Music?>, t: Throwable) {
//                Log.e("MainActivity", "Error: ${t.message}", t)
//            }
//        })
//    }
//}

//<?xml version="1.0" encoding="utf-8"?>
//<androidx.constraintlayout.widget.ConstraintLayout
//xmlns:android="http://schemas.android.com/apk/res/android"
//xmlns:app="http://schemas.android.com/apk/res-auto"
//android:layout_width="match_parent"
//android:layout_height="match_parent"
//android:padding="5dp">
//
//<!-- Album Info Section -->
//
//<ImageView
//android:id="@+id/image_album_cover"
//android:layout_width="150dp"
//android:layout_height="150dp"
//android:padding="16dp"
//android:scaleType="fitXY"
//app:layout_constraintDimensionRatio="1:1"
//app:layout_constraintEnd_toStartOf="@+id/text_album_title"
//app:layout_constraintStart_toStartOf="parent"
//app:layout_constraintTop_toTopOf="parent" />
//
//<TextView
//android:id="@+id/text_album_title"
//android:layout_width="0dp"
//android:layout_height="wrap_content"
//android:paddingTop="16dp"
//android:text="@string/album_title"
//android:textSize="18sp"
//android:textStyle="bold"
//app:layout_constraintEnd_toEndOf="parent"
//app:layout_constraintStart_toEndOf="@+id/image_album_cover"
//app:layout_constraintTop_toTopOf="@+id/image_album_cover" />
//
//<TextView
//android:id="@+id/text_artist_name"
//android:layout_width="0dp"
//android:layout_height="wrap_content"
//android:layout_marginTop="12dp"
//android:paddingTop="8dp"
//android:text="@string/artist_name"
//android:textSize="16sp"
//app:layout_constraintEnd_toEndOf="parent"
//app:layout_constraintStart_toEndOf="@+id/image_album_cover"
//app:layout_constraintTop_toBottomOf="@+id/text_album_title" />
//
//<androidx.recyclerview.widget.RecyclerView
//android:id="@+id/recyclerView"
//android:layout_width="0dp"
//android:layout_height="0dp"
//android:padding="16dp"
//app:layout_constraintBottom_toTopOf="@+id/nameEditText"
//app:layout_constraintEnd_toEndOf="parent"
//app:layout_constraintStart_toStartOf="parent"
//app:layout_constraintTop_toBottomOf="@+id/image_album_cover" />
//
//<EditText
//android:id="@+id/nameEditText"
//android:layout_width="0dp"
//android:layout_height="wrap_content"
//android:hint="@string/enter_name_of_artist"
//android:padding="16dp"
//app:layout_constraintBottom_toBottomOf="parent"
//app:layout_constraintEnd_toStartOf="@+id/searchButton"
//app:layout_constraintStart_toStartOf="parent"
//app:layout_constraintTop_toTopOf="@+id/searchButton" />
//
//<Button
//android:id="@+id/searchButton"
//android:layout_width="wrap_content"
//android:layout_height="wrap_content"
//android:text="@string/search"
//app:layout_constraintBottom_toBottomOf="parent"
//app:layout_constraintEnd_toEndOf="parent" />
//
//</androidx.constraintlayout.widget.ConstraintLayout>