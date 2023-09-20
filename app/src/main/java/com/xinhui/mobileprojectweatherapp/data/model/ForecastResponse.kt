package com.xinhui.mobileprojectweatherapp.data.model

data class ForecastResponse(
    val city_name: String,
    val country_code: String,
    val `data`: List<ForecastData>,
    val lat: String,
    val lon: String,
    val state_code: String,
    val timezone: String
)