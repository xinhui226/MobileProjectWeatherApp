package com.xinhui.mobileprojectweatherapp.ui.viewModels

import android.util.Log
import androidx.lifecycle.viewModelScope
import com.xinhui.mobileprojectweatherapp.data.repository.RetrofitRepo
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

abstract class BaseLocationViewModel(
    protected val repo: RetrofitRepo
) : BaseViewModel() {

    init {
        showCurrentForecastWeather()
    }

    fun showCurrentForecastWeather() {
        viewModelScope.launch(Dispatchers.IO) {
            safeApiCall {
                repo.getCurrWeather("Georgetown, Malaysia")
            }?.let {
                currWeather.value = it
            }

            safeApiCall {
                repo.getForecastWeather("Georgetown, Malaysia")
            }?.let {
                forecastWeather.value = it
            }
        }
    }
}