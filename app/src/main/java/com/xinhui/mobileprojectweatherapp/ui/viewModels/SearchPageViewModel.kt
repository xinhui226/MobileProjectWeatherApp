package com.xinhui.mobileprojectweatherapp.ui.viewModels

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewmodel.initializer
import androidx.lifecycle.viewmodel.viewModelFactory
import com.xinhui.mobileprojectweatherapp.MyApplication
import com.xinhui.mobileprojectweatherapp.data.model.Location
import com.xinhui.mobileprojectweatherapp.data.repository.LocationRepo
import kotlinx.coroutines.flow.Flow

class SearchPageViewModel(
    val locationRepo: LocationRepo,
) : ViewModel() {

    fun getLocations(): Flow<List<Location>> {
        return locationRepo.getLocations()
    }

    companion object {
        val Factory: ViewModelProvider.Factory = viewModelFactory {
            initializer {
                val locationRepository =
                    (this[ViewModelProvider.AndroidViewModelFactory.APPLICATION_KEY] as MyApplication).locationRepo
                SearchPageViewModel(
                    locationRepo = locationRepository,
                )
            }
        }
    }
}