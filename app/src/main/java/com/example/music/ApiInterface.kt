package com.example.music

import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Headers
import retrofit2.http.Query


interface ApiInterface {
    @Headers("X-RapidAPI-Key: 068932d965msh16def0507c222a8p1169fcjsn2aff44b1b2b0",
        "X-RapidAPI-Host: deezerdevs-deezer.p.rapidapi.com")
    @GET("search")
    fun getData(@Query("q")query:String): Call<Music>
}