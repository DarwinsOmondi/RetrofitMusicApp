package com.example.music.RoomDataBase

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class SearchViewModel(application: Application):AndroidViewModel(application) {
     val readAllSearchData:LiveData<List<SearchData>>
    private val searchRepository:SearchRepository
    init {
        val searchDao = SearchDataBase.getDataBase(application).searchDao()
        searchRepository = SearchRepository(searchDao)
        readAllSearchData = searchRepository.readAllSearchData
    }
    fun insertSearchData(search:SearchData){
        viewModelScope.launch (Dispatchers.IO){
            searchRepository.insertSearchData(search)
        }
    }
    fun deleteSearchData(search: SearchData){
        viewModelScope.launch(Dispatchers.IO) {
            searchRepository.deleteSearchData(search)
        }
    }
}