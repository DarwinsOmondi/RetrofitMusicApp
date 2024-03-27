package com.example.music

import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.Adapter
import androidx.navigation.findNavController
import androidx.recyclerview.widget.RecyclerView
import com.example.music.RoomDataBase.SearchData
import com.example.music.databinding.SearchItemBinding

class SearchAdapter : RecyclerView.Adapter<SearchAdapter.SearchViewHolder>() {
    private var searchList: List<SearchData> = emptyList()

    inner class SearchViewHolder(private val binding: SearchItemBinding) :
        RecyclerView.ViewHolder(binding.root) {

        init {
            itemView.setOnClickListener {
                val artistPosition = adapterPosition
                if (artistPosition != RecyclerView.NO_POSITION){
                    val artist = searchList[artistPosition]
                    val artistName = artist.artist
                    val action = SearchFragmentDirections.actionSearchFragmentToHomeFragment(artistName)
                    itemView.findNavController().navigate(action)
                }
            }
        }
        fun bind(search: SearchData) {
            binding.artistTextView.text = search.artist
        }
    }

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): SearchViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val binding = SearchItemBinding.inflate(inflater, parent, false)
        return SearchViewHolder(binding)
    }

    override fun onBindViewHolder(holder: SearchViewHolder, position: Int) {
        holder.bind(searchList[position])
    }

    override fun getItemCount(): Int {
        return searchList.size
    }

    fun setData(searchList: List<SearchData>) {
        this.searchList = searchList
        notifyDataSetChanged()
    }
}
