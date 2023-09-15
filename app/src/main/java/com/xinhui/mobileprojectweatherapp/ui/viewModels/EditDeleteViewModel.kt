package com.xinhui.mobileprojectweatherapp.ui.viewModels

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewModelScope
import androidx.lifecycle.viewmodel.initializer
import androidx.lifecycle.viewmodel.viewModelFactory
import com.xinhui.mobileprojectweatherapp.MyApplication
import com.xinhui.mobileprojectweatherapp.data.model.Location
import com.xinhui.mobileprojectweatherapp.data.repository.LocationRepo
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.launch

class EditDeleteViewModel(
    val locationRepo: LocationRepo,
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

    companion object {
        val Factory: ViewModelProvider.Factory = viewModelFactory {
            initializer {
                val locationRepository =
                    (this[ViewModelProvider.AndroidViewModelFactory.APPLICATION_KEY] as MyApplication)
                        .locationRepo
                EditDeleteViewModel(
                    locationRepo = locationRepository,
                )
            }
        }
    }
}