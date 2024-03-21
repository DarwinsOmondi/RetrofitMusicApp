package com.example.music.RoomDataBase

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query

@Dao
interface SearchDao {
    @Insert
    fun insertSearch(search: SearchData)
    @Delete
    fun deleteSerach(search: SearchData)
    @Query("SELECT * FROM SearchData")
    fun getAllSearchData():LiveData<List<SearchData>>
}