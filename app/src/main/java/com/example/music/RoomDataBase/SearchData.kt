package com.example.music.RoomDataBase

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class SearchData(
    @PrimaryKey(autoGenerate = true)
    val id:Int,
    val artist:String,
)
