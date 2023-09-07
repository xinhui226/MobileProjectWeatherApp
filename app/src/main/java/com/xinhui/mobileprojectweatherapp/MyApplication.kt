package com.xinhui.mobileprojectweatherapp

import android.app.Application
import androidx.room.Room
import com.xinhui.mobileprojectweatherapp.data.db.LocationDatabase
import com.xinhui.mobileprojectweatherapp.data.remote.RetrofitHelper
import com.xinhui.mobileprojectweatherapp.data.repository.LocationRepo
import com.xinhui.mobileprojectweatherapp.data.repository.RetrofitRepo
import com.xinhui.mobileprojectweatherapp.data.repository.WeatherApi

class MyApplication:Application() {
   private val weatherApi: WeatherApi= RetrofitHelper.getInstance().create(WeatherApi::class.java)
   lateinit var retrofitRepo: RetrofitRepo
   lateinit var locationRepo: LocationRepo

    override fun onCreate() {
        super.onCreate()

        val locationDatabase = Room.databaseBuilder(this,
            LocationDatabase::class.java, LocationDatabase.dbName)
            .build()

        retrofitRepo = RetrofitRepo(weatherApi)
        locationRepo = LocationRepo(locationDatabase.dao)
    }
}