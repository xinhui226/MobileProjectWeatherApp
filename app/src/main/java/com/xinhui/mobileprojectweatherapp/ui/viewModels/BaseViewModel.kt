package com.xinhui.mobileprojectweatherapp.ui.viewModels

import android.util.Log
import androidx.lifecycle.ViewModel
import com.xinhui.mobileprojectweatherapp.data.model.CurrentWeatherDisplay
import com.xinhui.mobileprojectweatherapp.data.model.ForecastWeatherDisplay
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.MutableStateFlow

abstract class BaseViewModel() : ViewModel() {
    val finishLoading: MutableSharedFlow<Unit> = MutableSharedFlow()
    val error: MutableSharedFlow<String> = MutableSharedFlow()

    val currWeather: MutableStateFlow<CurrentWeatherDisplay> = MutableStateFlow(
        CurrentWeatherDisplay()
    )

    val forecastWeather: MutableStateFlow<List<ForecastWeatherDisplay>> = MutableStateFlow(
        listOf(ForecastWeatherDisplay())
    )


    suspend fun <T>safeApiCall(callback: suspend () -> T?): T? {
        return try {
            Log.d("debugging", "Hello")
            callback()
        } catch (e: Exception) {
            e.printStackTrace()
            error.emit(e.message.toString())
            null
        }
    }


}