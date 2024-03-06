package com.example.android5_8.ui.search

import android.os.Bundle
import android.view.KeyEvent
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.inputmethod.EditorInfo
import androidx.fragment.app.viewModels
import com.example.android5_8.WeatherViewModel
import com.example.android5_8.databinding.FragmentSearchBinding
import com.example.android5_8.remote.SearchedWeatherModel
import com.example.android5_8.ui.home.bottomSheet.recyclerview.SearchedWeatherAdapter
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class SearchFragment : Fragment() {

    private lateinit var binding: FragmentSearchBinding
    private val viewModel: WeatherViewModel by viewModels()
    lateinit var adapter: SearchedWeatherAdapter


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentSearchBinding.inflate(layoutInflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initSearch()
        observe()
    }

    private fun observe() {
        viewModel.cityWeatherLiveData.observe(viewLifecycleOwner) {
            if (it != null) {
                    adapter =
                        it?.let { it1 -> SearchedWeatherAdapter(it1 as ArrayList<SearchedWeatherModel>) }!!
                    binding.rvSearch.adapter = adapter
            }
        }
    }

    private fun initSearch() {
        binding.btnSearch.setOnClickListener {
            viewModel.getWeatherByCity(binding.etSearch.text.toString())
            binding.etSearch.setText("")
        }
    }
}