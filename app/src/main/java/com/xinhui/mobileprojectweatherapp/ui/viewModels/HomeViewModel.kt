package com.xinhui.mobileprojectweatherapp.ui.viewModels

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelProvider.AndroidViewModelFactory.Companion.APPLICATION_KEY
import androidx.lifecycle.viewModelScope
import androidx.lifecycle.viewmodel.initializer
import androidx.lifecycle.viewmodel.viewModelFactory
import com.xinhui.mobileprojectweatherapp.MyApplication
import com.xinhui.mobileprojectweatherapp.data.model.CurrentWeatherDisplay
import com.xinhui.mobileprojectweatherapp.data.model.ForecastWeatherDisplay
import com.xinhui.mobileprojectweatherapp.data.repository.RetrofitRepo
import com.xinhui.mobileprojectweatherapp.ui.util.Constant.TAG
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharedFlow
import kotlinx.coroutines.launch

class HomeViewModel(
    private val repo: RetrofitRepo
) : ViewModel() {
    val finishLoading: MutableSharedFlow<Unit> = MutableSharedFlow()

    val currWeather: MutableStateFlow<CurrentWeatherDisplay> = MutableStateFlow(
        CurrentWeatherDisplay()
    )

    val forecastWeather: MutableStateFlow<List<ForecastWeatherDisplay>> = MutableStateFlow(
        listOf(ForecastWeatherDisplay())
    )

    init {
        showCurrentForecastWeather()
    }

    fun showCurrentForecastWeather() {
        viewModelScope.launch(Dispatchers.IO) {
            try {
                currWeather.value = repo.getCurrWeather("Osaka,Japan")
                forecastWeather.value = repo.getForecastWeather("Osaka,Japan")
                finishLoading.emit(Unit)
            } catch (e: Exception) {
                e.printStackTrace()
            }
        }
    }

//    fun showForecastWeather() {
//        viewModelScope.launch(Dispatchers.IO) {
//            try {
//                forecastWeather.value = repo.getForecastWeather("Kampung Kok, Malaysia")
//            } catch (e: Exception) {
//                e.printStackTrace()
//            }
//        }
//    }

    companion object {
        val Factory: ViewModelProvider.Factory = viewModelFactory {
            initializer {
                val retrofitRepository =
                    (this[APPLICATION_KEY] as MyApplication).retrofitRepo
                HomeViewModel(
                    repo = retrofitRepository,
                )
            }
        }
    }
}