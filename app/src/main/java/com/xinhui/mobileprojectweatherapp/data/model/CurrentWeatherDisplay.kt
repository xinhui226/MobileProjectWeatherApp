package com.xinhui.mobileprojectweatherapp.data.model

data class CurrentWeatherDisplay(
    val cityName:String,
    val airQuality:Int,
    val humidity: Int,
    val temp: Int,
    val uv: Int,
    val weather: Weather,
    val windSpeed: Int,
    val timezone:String,
    val timestamp:String,
)
