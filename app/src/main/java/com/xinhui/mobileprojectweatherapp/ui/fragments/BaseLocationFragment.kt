package com.xinhui.mobileprojectweatherapp.ui.fragments

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.lifecycleScope
import androidx.navigation.NavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.bumptech.glide.Glide
import com.xinhui.mobileprojectweatherapp.BuildConfig
import com.xinhui.mobileprojectweatherapp.R
import com.xinhui.mobileprojectweatherapp.ui.adapters.HorizontalRVAdapter
import com.xinhui.mobileprojectweatherapp.ui.viewModels.BaseLocationViewModel
import  com.xinhui.mobileprojectweatherapp.databinding.ShowWeatherBinding
import kotlinx.coroutines.launch

abstract class BaseLocationFragment : BaseFragment() {
    abstract override val viewModel : BaseLocationViewModel

    private lateinit var adapter: HorizontalRVAdapter
    protected lateinit var binding: ShowWeatherBinding
    protected lateinit var navController: NavController


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?
    ): View? {
        binding = ShowWeatherBinding.inflate(layoutInflater)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val swipeRefreshLayout = binding.swipeRefresh

        binding.viewModel = viewModel
        binding.lifecycleOwner = viewLifecycleOwner

        Log.d("debugging", "Hello")

        swipeRefreshLayout.setOnRefreshListener {
            refreshData()
        }


        lifecycleScope.launch {
            viewModel.forecastWeather.collect {
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
                    .with(this@BaseLocationFragment)
                    .load("${BuildConfig.BaseIconUrl}${it.weather.icon}.png")
                    .placeholder(R.drawable.ic_image)
                    .into(binding.ivIconImage)
            }
        }
    }

    fun setupAdapter() {

        adapter = HorizontalRVAdapter(viewModel.forecastWeather.value)

        binding.horizontalRecyclerView.adapter = adapter

        val layoutManager =
            LinearLayoutManager(requireContext(), LinearLayoutManager.HORIZONTAL, false)
        binding.horizontalRecyclerView.layoutManager = layoutManager
    }

    fun refreshData() {
        binding.swipeRefresh.isRefreshing = true
        viewModel.showCurrentForecastWeather()
    }
}
