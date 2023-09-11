package com.xinhui.mobileprojectweatherapp.data.model

data class CurrentWeatherDisplay(
    val cityName:String="",
    val airQuality:Int=0,
    val humidity: Int=0,
    val temp: Int=0,
    val uv: Int=0,
    val weather: Weather=Weather(0,"",""),
    val windSpeed: Int=0,
    val timezone:String="",
    val timestamp:String="",
)
