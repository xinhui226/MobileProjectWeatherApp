package com.xinhui.mobileprojectweatherapp.data.model

data class ForecastWeatherDisplay(
    val temp: String = "0",
    val weather: Weather = Weather(0, "", ""),
    val timeStamp: String = ""
)
