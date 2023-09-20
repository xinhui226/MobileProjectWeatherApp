package com.xinhui.mobileprojectweatherapp.data.model

data class CurrentWeatherDisplay(
    val cityName:String = "City Name",
    val airQuality:String = "0",
    val humidity: String = "0",
    val temp: String = "0",
    val tempFah: String = "0",
    val uv: String = "0",
    val weather: Weather = Weather(0, "", ""),
    val windSpeed: String = "0",
    val timezone:String = "",
    val timestamp:String = "",
)
