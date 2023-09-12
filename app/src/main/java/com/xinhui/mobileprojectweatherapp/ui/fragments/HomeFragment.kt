package com.xinhui.mobileprojectweatherapp.ui.fragments

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import com.bumptech.glide.Glide
import com.xinhui.mobileprojectweatherapp.R
import com.xinhui.mobileprojectweatherapp.adapter.HorizontalRVAdapter
import com.xinhui.mobileprojectweatherapp.databinding.ShowWeatherBinding
import com.xinhui.mobileprojectweatherapp.ui.viewModels.HomeViewModel
import kotlinx.coroutines.launch

class HomeFragment : Fragment() {
    private lateinit var binding: ShowWeatherBinding
    private val viewModel: HomeViewModel by viewModels {
        HomeViewModel.Factory
    }

    private lateinit var adapter: HorizontalRVAdapter

    private val baseIconUrl = "https://cdn.weatherbit.io/static/img/icons/"

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?
    ): View? {
        binding = ShowWeatherBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.viewModel = viewModel
        binding.lifecycleOwner = viewLifecycleOwner

        val swipeRefreshLayout = binding.swipeRefresh

        swipeRefreshLayout.setOnRefreshListener {
            refreshData()
        }

        lifecycleScope.launch {
            viewModel.forecastWeather.collect{
                setupAdapter()
            }
        }

        lifecycleScope.launch {
            viewModel.finishLoading.collect {
                binding.swipeRefresh.isRefreshing = false
            }
        }

        lifecycleScope.launch {
            viewModel.currWeather.collect {
                Glide
                    .with(this@HomeFragment)
                    .load("$baseIconUrl${it.weather.icon}.png")
                    .placeholder(R.drawable.ic_image)
                    .into(binding.ivIconImage)
            }
        }
    }

    fun setupAdapter() {

        adapter = HorizontalRVAdapter(viewModel.forecastWeather.value)

        binding.horizontalRecyclerView.adapter = adapter

        val layoutManager = LinearLayoutManager(requireContext(), LinearLayoutManager.HORIZONTAL, false)
        binding.horizontalRecyclerView.layoutManager = layoutManager
    }

    fun refreshData() {
        binding.swipeRefresh.isRefreshing = true
        viewModel.showCurrentForecastWeather()
    }
}