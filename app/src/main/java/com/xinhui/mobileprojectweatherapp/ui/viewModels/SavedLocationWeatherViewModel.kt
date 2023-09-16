package com.xinhui.mobileprojectweatherapp.ui.viewModels

import android.os.Build
import androidx.annotation.RequiresApi
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelProvider.AndroidViewModelFactory.Companion.APPLICATION_KEY
import androidx.lifecycle.viewModelScope
import androidx.lifecycle.viewmodel.initializer
import androidx.lifecycle.viewmodel.viewModelFactory
import com.xinhui.mobileprojectweatherapp.MyApplication
import com.xinhui.mobileprojectweatherapp.data.model.Location
import com.xinhui.mobileprojectweatherapp.data.repository.LocationRepo
import com.xinhui.mobileprojectweatherapp.data.repository.RetrofitRepo
import com.xinhui.mobileprojectweatherapp.ui.util.localtimeFormatter
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.launch

class SavedLocationWeatherViewModel(
    repo: RetrofitRepo,
    private val locationRepo: LocationRepo
) : BaseLocationViewModel(repo) {
    override var city = ""
    var locationId:Int = 0
    val locationSaved: MutableStateFlow<Boolean> = MutableStateFlow(false)

    fun check(){
        viewModelScope.launch(Dispatchers.IO) {
            val searchCity = repo.getCurrWeather(city).cityName
            locationRepo.getLocations().collect{
                it.forEach { location ->
                    if(searchCity == location.city) locationSaved.value = true
                }
            }
        }
    }

    @RequiresApi(Build.VERSION_CODES.O)
    fun addLocation(){
        viewModelScope.launch(Dispatchers.IO){
            val locations = locationRepo.getLocations().first().toList()
            if (locations.size > 5){
                error.emit("You have over the limit(5). Please remove to add new location.")
            }
            else{
                val location = repo.getCurrWeather(city)
                locationRepo.savedLocation(
                    Location(city = location.cityName,
                        temp = location.temp,
                        localtime = localtimeFormatter(location.timezone,location.timestamp),
                        weatherDesc = location.weather.description)
                )
                locationSaved.value = true
            }
        }
    }

    fun removeLocation() {
        viewModelScope.launch(Dispatchers.IO) {
            locationRepo.getLocationById(locationId)?.let {
                locationRepo.removeSavedLocation(it)
            }
        }
        locationSaved.value = false
    }

    companion object {
        val Factory: ViewModelProvider.Factory = viewModelFactory {
            initializer {
                val retrofitRepository =
                    (this[APPLICATION_KEY] as MyApplication).retrofitRepo
                val locationRepository =
                    (this[APPLICATION_KEY] as MyApplication).locationRepo
                SavedLocationWeatherViewModel(
                    repo = retrofitRepository,
                    locationRepo = locationRepository
                )
            }
        }
    }

}