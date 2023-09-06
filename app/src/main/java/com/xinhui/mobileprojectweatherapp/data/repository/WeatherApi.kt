package com.xinhui.mobileprojectweatherapp.data.repository

import com.xinhui.mobileprojectweatherapp.BuildConfig
import com.xinhui.mobileprojectweatherapp.data.model.CurrentResponse
import com.xinhui.mobileprojectweatherapp.data.model.ForecastResponse
import retrofit2.http.GET
import retrofit2.http.Query

interface WeatherApi {

    @GET("current")
    suspend fun getCurrentResponse(@Query("key") key: String = BuildConfig.API_KEY, @Query(value = "city", encoded = true) city: String): CurrentResponse

    @GET("forecast/hourly")
    suspend fun getForecastResponse(@Query("key") key: String=BuildConfig.API_KEY, @Query(value = "city", encoded = true) city: String, @Query("hours") hours:String = "24") : ForecastResponse
}
