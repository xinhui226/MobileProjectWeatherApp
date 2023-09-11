package com.xinhui.mobileprojectweatherapp.data.model

data class CurrentData(
    val app_temp: Double,
    val aqi: Int,
    val city_name: String,
    val clouds: Int,
    val country_code: String,
    val datetime: String,
    val dewpt: Double,
    val dhi: Double,
    val dni: Double,
    val elev_angle: Double,
    val ghi: Double,
    val gust: Any,
    val h_angle: Int,
    val lat: Double,
    val lon: Double,
    val ob_time: String,
    val pod: String,
    val precip: Double,
    val pres: Double,
    val rh: Int,
    val slp: Double,
    val snow: Int,
    val solar_rad: Double,
    val sources: List<String>,
    val state_code: String,
    val station: String,
    val sunrise: String,
    val sunset: String,
    val temp: Double,
    val timezone: String,
    val ts: Int,
    val uv: Double,
    val vis: Int,
    val weather: Weather,
    val wind_cdir: String,
    val wind_cdir_full: String,
    val wind_dir: Int,
    val wind_spd: Double
) {
    fun toCurrentWeatherDisplay(): CurrentWeatherDisplay {
        return CurrentWeatherDisplay(
            city_name,
            aqi.toString(),
            rh.toString()+"%",
            temp.toInt().toString()+"°C",
            ((temp.toInt())*9/5+32).toString()+"°F",
            uv.toInt().toString(),
            weather,
            (wind_spd.toInt()*3600/1000).toString()+"km/h",
            timezone,
            ob_time
        )
    }
}