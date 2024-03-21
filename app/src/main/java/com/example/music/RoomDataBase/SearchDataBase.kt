package com.example.music.RoomDataBase

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase

@Database(
    entities = [SearchData::class],
    version = 1
)
abstract class SearchDataBase:RoomDatabase() {
    abstract fun searchDao():SearchDao
    companion object{
        @Volatile
        private var INSTANCE:SearchDataBase? = null
        fun getDataBase(context: Context):SearchDataBase{
            val tempInstance = INSTANCE
            if (tempInstance != null){
                return tempInstance
            }
            synchronized(this){
                val instnace = Room.databaseBuilder(
                    context.applicationContext,
                    SearchDataBase::class.java,
                    "search_database"
                ).fallbackToDestructiveMigration()
                    .build()
                INSTANCE = instnace
                return instnace
            }
        }
    }
}