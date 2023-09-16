package com.xinhui.mobileprojectweatherapp.ui.viewModels

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelProvider.AndroidViewModelFactory.Companion.APPLICATION_KEY
import androidx.lifecycle.viewmodel.initializer
import androidx.lifecycle.viewmodel.viewModelFactory
import com.xinhui.mobileprojectweatherapp.MyApplication
import com.xinhui.mobileprojectweatherapp.data.repository.LocationRepo

class SearchPageViewModel(
    val locationRepo: LocationRepo,
) : ViewModel() {



    companion object {
        val Factory: ViewModelProvider.Factory = viewModelFactory {
            initializer {
                val locationRepository =
                    (this[APPLICATION_KEY] as MyApplication).locationRepo
                SearchPageViewModel(
                    locationRepo = locationRepository,
                )
            }
        }
    }
}