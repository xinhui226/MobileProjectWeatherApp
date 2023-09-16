package com.xinhui.mobileprojectweatherapp.ui.fragments

import android.os.Build
import android.os.Bundle
import android.view.View
import androidx.annotation.RequiresApi
import androidx.core.view.isVisible
import androidx.fragment.app.viewModels
import androidx.lifecycle.asLiveData
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.fragment.navArgs
import com.xinhui.mobileprojectweatherapp.ui.viewModels.SavedLocationWeatherViewModel

class SavedLocationWeatherFragment() : BaseLocationFragment() {

    private val navArgs:SavedLocationWeatherFragmentArgs by navArgs()

    override val viewModel: SavedLocationWeatherViewModel by viewModels {
        SavedLocationWeatherViewModel.Factory
    }

    @RequiresApi(Build.VERSION_CODES.O)
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        viewModel.city = navArgs.city
        viewModel.locationId = navArgs.locationId
        viewModel.check()
        viewModel.showCurrentForecastWeather()

        viewModel.finishLoading.asLiveData().observe(viewLifecycleOwner){
            binding.run {
                llLoading.visibility = View.GONE
                clShowWeather.visibility = View.VISIBLE
            }
        }

        viewModel.locationSaved.asLiveData().observe(viewLifecycleOwner){
            binding.run {
                tvSave.visibility = if(it) View.GONE else View.VISIBLE
                tvRemove.visibility = if(!it) View.GONE else View.VISIBLE
                tvSave.setOnClickListener {
                    this@SavedLocationWeatherFragment.viewModel.addLocation()
                }
                tvRemove.setOnClickListener {
                    this@SavedLocationWeatherFragment.viewModel.removeLocation()
                }
            }
        }

        navController = NavHostFragment.findNavController(this)

        binding.run {
            ivListItemMenu.isVisible = false
            tvCancel.isVisible = true
            tvCancel.setOnClickListener {
                navController.popBackStack()
            }
        }

    }
}