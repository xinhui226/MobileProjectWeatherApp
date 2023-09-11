package com.xinhui.mobileprojectweatherapp.data.model

data class ForecastWeatherDisplay(
    val temp: Int=0,
    val weather: Weather=Weather(0,"",""),
    val timeStamp: String=""
)
