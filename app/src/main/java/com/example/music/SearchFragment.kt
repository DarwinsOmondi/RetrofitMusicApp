package com.example.music

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.music.RoomDataBase.SearchData
import com.example.music.RoomDataBase.SearchViewModel
import com.example.music.databinding.FragmentSearchBinding

class SearchFragment : Fragment() {

    private lateinit var binding: FragmentSearchBinding
    private lateinit var searchAdapter: SearchAdapter
    private lateinit var recyclerView: RecyclerView
    private lateinit var searchbutton: Button
    private lateinit var editText: EditText
    private lateinit var clearTextView: TextView
    private lateinit var searchViewModel: SearchViewModel
    private var searchList: List<SearchData> = emptyList()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentSearchBinding.inflate(inflater, container, false)

        searchAdapter = SearchAdapter()
        recyclerView = binding.recyclerView
        recyclerView.adapter = searchAdapter
        recyclerView.layoutManager = LinearLayoutManager(requireContext())

        editText = binding.editTextText
        searchbutton = binding.searchButton

        searchViewModel = ViewModelProvider(this)[SearchViewModel::class.java]

        searchbutton.setOnClickListener {
            val name = editText.text.toString()
            if (name.isNotEmpty()) {
                val newSearch = SearchData(0,name)
                searchViewModel.insertSearchData(newSearch)
                editText.text.clear()
            }
            val action = SearchFragmentDirections.actionSearchFragmentToHomeFragment(name)
            findNavController().navigate(action)
        }
        clearTextView = binding.clearTextView
        clearTextView.setOnClickListener {
            searchViewModel.deleteSearchData()
        }

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        searchViewModel.readAllSearchData.observe(viewLifecycleOwner) { search ->
            this.searchList = search
            searchAdapter.setData(search)
        }
    }
}
