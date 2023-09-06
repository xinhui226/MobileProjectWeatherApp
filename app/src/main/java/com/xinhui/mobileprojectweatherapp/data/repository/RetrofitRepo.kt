package com.xinhui.mobileprojectweatherapp.data.repository

import com.xinhui.mobileprojectweatherapp.data.model.CurrentResponse
import com.xinhui.mobileprojectweatherapp.data.model.CurrentWeatherDisplay
import com.xinhui.mobileprojectweatherapp.data.model.ForecastWeatherDisplay
import retrofit2.Response

class RetrofitRepo(private val api: WeatherApi) {

    suspend fun getCurrWeather(city:String): CurrentWeatherDisplay {
        return api.getCurrentResponse(city = city).data[0].toCurrentWeatherDisplay()
    }

    suspend fun getForecastWeather(city: String):List<ForecastWeatherDisplay>{
        return api.getForecastResponse(city = city).data.map {
            it.toForecastWeatherDisplay()
        }
    }

}