package com.xinhui.mobileprojectweatherapp.ui.viewModels

import android.util.Log
import androidx.lifecycle.viewModelScope
import com.xinhui.mobileprojectweatherapp.data.repository.RetrofitRepo
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

abstract class BaseLocationViewModel(
    protected val repo: RetrofitRepo,
) : BaseViewModel() {

    abstract val city:String
    fun showCurrentForecastWeather() {
        viewModelScope.launch(Dispatchers.IO) {
            safeApiCall {
                repo.getCurrWeather(city)
            }?.let {
                currWeather.value = it
                finishLoading.emit(Unit)
            }

            safeApiCall {
                repo.getForecastWeather(city)
            }?.let {
                forecastWeather.value = it
                finishLoading.emit(Unit)
            }
        }
    }
}