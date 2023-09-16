package com.xinhui.mobileprojectweatherapp.ui.viewModels

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
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.launch

class HomeViewModel(
    private val repo: RetrofitRepo
) : ViewModel() {
    val finishLoading: MutableSharedFlow<Unit> = MutableSharedFlow()

    val currWeather: MutableStateFlow<CurrentWeatherDisplay> =
        MutableStateFlow(CurrentWeatherDisplay())

    val forecastWeather: MutableStateFlow<List<ForecastWeatherDisplay>> =
        MutableStateFlow(listOf(ForecastWeatherDisplay()))

    init {
        showCurrentForecastWeather()
    }

    fun showCurrentForecastWeather() {
        viewModelScope.launch(Dispatchers.IO) {
            try {
                currWeather.value = repo.getCurrWeather(
                    "Georgetown,Malaysia")
                forecastWeather.value = repo.getForecastWeather(
                    "Georgetown,Malaysia")
                finishLoading.emit(Unit)
            } catch (e: Exception) {
                e.printStackTrace()
            }
        }
    }

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