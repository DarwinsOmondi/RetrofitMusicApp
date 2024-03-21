package com.example.music.RoomDataBase

import androidx.lifecycle.LiveData

class SearchRepository(private val searchDao: SearchDao) {
    val readAllSearchData:LiveData<List<SearchData>> = searchDao.getAllSearchData()

     fun insertSearchData(search: SearchData){
        searchDao.insertSearch(search)
    }
     fun deleteSearchData(search: SearchData){
        searchDao.deleteSerach(search)
    }
}