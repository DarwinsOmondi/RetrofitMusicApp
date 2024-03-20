package com.example.music

import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.music.databinding.MusicItemBinding
import java.util.Date

class MusicAdapter():RecyclerView.Adapter<MusicAdapter.MusicViewHolder>() {
    private var music:List<Data> = emptyList()
  inner class MusicViewHolder(private val binding: MusicItemBinding):RecyclerView.ViewHolder(binding.root) {
      fun bind(music:Data){
          binding.apply {
              songNameText.text = music.title
              songDurationText.text = music.duration.toString()
          }
      }
    }

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ):MusicViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val binding = MusicItemBinding.inflate(inflater,parent,false)
        return MusicViewHolder(binding)
    }

    override fun onBindViewHolder(holder: MusicAdapter.MusicViewHolder, position: Int) {
        val currentmusic = music[position]
        holder.bind(currentmusic)
    }

    override fun getItemCount(): Int {
        return music.size
    }
    fun setData(music:List<Data>){
        this.music = music
        notifyDataSetChanged()
    }
}