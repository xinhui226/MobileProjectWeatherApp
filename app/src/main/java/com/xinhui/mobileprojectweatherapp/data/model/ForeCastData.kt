package com.xinhui.mobileprojectweatherapp.data.model

data class ForecastData(
    val app_temp: Double,
    val clouds: Int,
    val clouds_hi: Int,
    val clouds_low: Int,
    val clouds_mid: Int,
    val datetime: String,
    val dewpt: Double,
    val dhi: Double,
    val dni: Double,
    val ghi: Double,
    val ozone: Double,
    val pod: String,
    val pop: Int,
    val precip: Double,
    val pres: Double,
    val rh: Int,
    val slp: Double,
    val snow: Int,
    val snow_depth: Int,
    val solar_rad: Double,
    val temp: Double,
    val timestamp_local: String,
    val timestamp_utc: String,
    val ts: Int,
    val uv: Double,
    val vis: Double,
    val weather: Weather,
    val wind_cdir: String,
    val wind_cdir_full: String,
    val wind_dir: Int,
    val wind_gust_spd: Double,
    val wind_spd: Double
){
    fun toForecastWeatherDisplay() :ForecastWeatherDisplay{
        return ForecastWeatherDisplay(
            temp.toInt(),
            weather,
            timestamp_local.split("T")[1]
        )
    }
}