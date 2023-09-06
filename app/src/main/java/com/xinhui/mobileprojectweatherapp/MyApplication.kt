package com.xinhui.mobileprojectweatherapp

import android.app.Application
import com.xinhui.mobileprojectweatherapp.data.remote.RetrofitHelper
import com.xinhui.mobileprojectweatherapp.data.repository.RetrofitRepo
import com.xinhui.mobileprojectweatherapp.data.repository.WeatherApi

class MyApplication:Application() {
   private val weatherApi: WeatherApi= RetrofitHelper.getInstance().create(WeatherApi::class.java)
   lateinit var retrofitRepo: RetrofitRepo

    override fun onCreate() {
        super.onCreate()
        retrofitRepo = RetrofitRepo(weatherApi)
    }
}