package com.xinhui.mobileprojectweatherapp.data.model

data class CurrentWeatherDisplay(
    val cityName:String,
    val airQuality:Int,
    val humidity: Int,
    val temp: Double,
    val uv: Double,
    val weather: Weather,
    val windSpeed: Double,
    val timezone:String,
    val timestamp:String,
)
