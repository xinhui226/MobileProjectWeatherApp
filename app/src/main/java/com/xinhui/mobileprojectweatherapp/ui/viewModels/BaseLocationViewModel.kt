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
        Log.d("debugging", "Hello")
        viewModelScope.launch(Dispatchers.IO) {
            safeApiCall {
                repo.getCurrWeather("Osaka, Japan")
            }?.let {
                currWeather.value = it
                Log.d("debugging", it.toString())
            }

            safeApiCall {
                repo.getForecastWeather("Osaka,Japan")
            }?.let {
                forecastWeather.value = it
            }
        }
    }
}