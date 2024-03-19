package com.example.music

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.TextView

class SongAdapter(context: Context, private val songs: List<Data>) :
    ArrayAdapter<Data>(context, 0, songs) {

    override fun getView(position: Int, convertView: View?, parent: ViewGroup): View {
        var listItemView = convertView
        if (listItemView == null) {
            listItemView = LayoutInflater.from(context).inflate(R.layout.song_item, parent, false)
        }

        val currentSong = songs[position]

        val songTitleTextView = listItemView!!.findViewById<TextView>(R.id.song_title)
        songTitleTextView.text = currentSong.title

        return listItemView
    }
}
