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
    @Query("DELETE FROM SearchData")
    fun deleteSearch()
    @Query("SELECT * FROM SearchData")
    fun getAllSearchData():LiveData<List<SearchData>>
}