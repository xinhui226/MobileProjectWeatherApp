package com.xinhui.mobileprojectweatherapp.ui.viewModels

import android.os.Build
import androidx.annotation.RequiresApi
import androidx.lifecycle.ViewModel
import com.xinhui.mobileprojectweatherapp.data.model.Location
import kotlinx.coroutines.flow.Flow
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelProvider.AndroidViewModelFactory.Companion.APPLICATION_KEY
import androidx.lifecycle.viewModelScope
import androidx.lifecycle.viewmodel.initializer
import androidx.lifecycle.viewmodel.viewModelFactory
import com.xinhui.mobileprojectweatherapp.MyApplication
import com.xinhui.mobileprojectweatherapp.data.repository.LocationRepo
import com.xinhui.mobileprojectweatherapp.data.repository.RetrofitRepo
import com.xinhui.mobileprojectweatherapp.ui.util.localtimeFormatter
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.launch

@RequiresApi(Build.VERSION_CODES.O)
class SearchPageViewModel(
    val locationRepo: LocationRepo,
    val retrofitRepo: RetrofitRepo
) : ViewModel() {

    init {
        loadLocationWeather()
    }

    private fun loadLocationWeather() {
        viewModelScope.launch(Dispatchers.IO) {
            locationRepo.getLocations().first().toList().forEach { location ->
                retrofitRepo.getCurrWeather(location.city).let {
                    locationRepo.updateLocationWeather(
                        location.id!!,
                        it.cityName,
                        it.temp,
                        localtimeFormatter(it.timezone,it.timestamp),
                        it.weather.description
                        )
                }
            }
        }
    }

    companion object {
        val Factory: ViewModelProvider.Factory = viewModelFactory {
            initializer {
                val locationRepository =
                    (this[APPLICATION_KEY] as MyApplication).locationRepo
                val retrofitRepository =
                    (this[APPLICATION_KEY] as MyApplication).retrofitRepo
                SearchPageViewModel(
                    locationRepo = locationRepository,
                    retrofitRepo = retrofitRepository
                )
            }
        }
    }


    fun getLocations(): Flow<List<Location>> {
        return locationRepo.getLocations()
    }
}