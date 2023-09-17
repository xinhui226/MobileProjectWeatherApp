package com.xinhui.mobileprojectweatherapp.ui.viewModels

import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelProvider.AndroidViewModelFactory.Companion.APPLICATION_KEY
import androidx.lifecycle.viewModelScope
import androidx.lifecycle.viewmodel.initializer
import androidx.lifecycle.viewmodel.viewModelFactory
import com.xinhui.mobileprojectweatherapp.MyApplication
import com.xinhui.mobileprojectweatherapp.data.model.Location
import com.xinhui.mobileprojectweatherapp.data.repository.LocationRepo
import com.xinhui.mobileprojectweatherapp.data.repository.RetrofitRepo
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class HomeViewModel(
    repo: RetrofitRepo,
    val locationRepo: LocationRepo
) : BaseLocationViewModel(repo) {
    override var city = "Georgetown,MY"
    init {
        showCurrentForecastWeather()
        savedInitLocation()
    }

    private fun savedInitLocation(){
        viewModelScope.launch(Dispatchers.IO) {
            val existLocation  = locationRepo.getLocationById(1)

            if (existLocation == null){
                currWeather.collect{
                    val location = Location(1,city,"George Town",it.temp,it.timestamp,it.weather.description)
                    locationRepo.savedLocation(location)
                    locationRepo.updateLocationPriority(1,0)
                }
            }
        }
    }

    companion object {
        val Factory: ViewModelProvider.Factory = viewModelFactory {
            initializer {
                val retrofitRepository =
                    (this[APPLICATION_KEY] as MyApplication).retrofitRepo
                val locationRepository =
                    (this[APPLICATION_KEY] as MyApplication).locationRepo
                HomeViewModel(
                    repo = retrofitRepository,
                    locationRepo = locationRepository
                )
            }
        }
    }
}