package com.xinhui.mobileprojectweatherapp.ui.viewModels

import android.os.Build
import androidx.annotation.RequiresApi
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewModelScope
import androidx.lifecycle.viewmodel.initializer
import androidx.lifecycle.viewmodel.viewModelFactory
import com.xinhui.mobileprojectweatherapp.MyApplication
import com.xinhui.mobileprojectweatherapp.data.model.Location
import com.xinhui.mobileprojectweatherapp.data.repository.LocationRepo
import com.xinhui.mobileprojectweatherapp.data.repository.RetrofitRepo
import com.xinhui.mobileprojectweatherapp.ui.util.localtimeFormatter
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.launch

class EditDeleteViewModel(
    val locationRepo: LocationRepo,
    val retrofitRepo: RetrofitRepo,
) : ViewModel() {

    private lateinit var firstLocation:Location
    private lateinit var secLocation:Location



    fun deleteLocation(location: Location){
        viewModelScope.launch(Dispatchers.IO) {
            locationRepo.removeSavedLocation(location)
        }
    }

    fun getLocations():Flow<List<Location>>{
          return locationRepo.getLocations()
    }

    fun updLocationPrio(from:Int,to:Int){
        viewModelScope.launch(Dispatchers.IO) {
               val locations = locationRepo.getLocations().first()
                firstLocation = locations[from]
                secLocation = locations[to]


                locationRepo.updateLocationPriority(firstLocation.id!!,to)
                locationRepo.updateLocationPriority(secLocation.id!!,from)
        }
    }

    @RequiresApi(Build.VERSION_CODES.O)
    fun addLocation(){
        viewModelScope.launch(Dispatchers.IO){
            val location = retrofitRepo.getCurrWeather("Gurun,MY")
            locationRepo.savedLocation(
                Location(city = location.cityName,
                    temp = location.temp,
                    localtime = localtimeFormatter(location.timezone,location.timestamp),
                    weatherDesc = location.weather.description))
            val location2 = retrofitRepo.getCurrWeather("SungaiPetani,MY")
            locationRepo.savedLocation(
                Location(city = location2.cityName,
                    temp = location2.temp,
                    localtime = localtimeFormatter(location2.timezone,location2.timestamp),
                    weatherDesc = location2.weather.description))
            val location3 = retrofitRepo.getCurrWeather("KualaLumpur,MY")
            locationRepo.savedLocation(
                Location(city = location3.cityName,
                    temp = location3.temp,
                    localtime = localtimeFormatter(location3.timezone,location3.timestamp),
                    weatherDesc = location3.weather.description))

            val location4 = retrofitRepo.getCurrWeather("Narro,Italy")
            locationRepo.savedLocation(
                Location(city = location4.cityName,
                    temp = location4.temp,
                    localtime = localtimeFormatter(location3.timezone,location3.timestamp),
                    weatherDesc = location4.weather.description))
        }
    }

    companion object {
        val Factory: ViewModelProvider.Factory = viewModelFactory {
            initializer {
                val locationRepository =
                    (this[ViewModelProvider.AndroidViewModelFactory.APPLICATION_KEY] as MyApplication)
                        .locationRepo
                val weatherRepository =
                    (this[ViewModelProvider.AndroidViewModelFactory.APPLICATION_KEY] as MyApplication)
                        .retrofitRepo
                EditDeleteViewModel(
                    locationRepo = locationRepository,
                    retrofitRepo = weatherRepository,
                )
            }
        }
    }
}