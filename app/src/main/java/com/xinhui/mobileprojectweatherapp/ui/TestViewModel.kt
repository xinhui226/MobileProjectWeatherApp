package com.xinhui.mobileprojectweatherapp.ui

import android.os.Build
import android.util.Log
import androidx.annotation.RequiresApi
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewModelScope
import androidx.lifecycle.viewmodel.initializer
import androidx.lifecycle.viewmodel.viewModelFactory
import com.xinhui.mobileprojectweatherapp.MyApplication
import com.xinhui.mobileprojectweatherapp.data.model.CurrentData
import com.xinhui.mobileprojectweatherapp.data.model.CurrentWeatherDisplay
import com.xinhui.mobileprojectweatherapp.data.repository.RetrofitRepo
import com.xinhui.mobileprojectweatherapp.ui.util.Constant.TAG
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import retrofit2.Response
import java.time.LocalDateTime
import java.time.ZoneId
import java.time.ZonedDateTime
import java.time.format.DateTimeFormatter
class TestViewModel(
    private val repo: RetrofitRepo,
) :ViewModel(){

    @RequiresApi(Build.VERSION_CODES.O)
    fun test() {
        viewModelScope.launch(Dispatchers.IO) {
            try {
                val city = "Georgetown,MY"
                val res = repo.getForecastWeather(city)
                res.map {
                    Log.d(TAG, it.toString())
                }
            }catch (e:Exception){
                Log.d(TAG, "smtg wrong...")
            }
        }
    }
    companion object {
        val Factory: ViewModelProvider.Factory = viewModelFactory {
            initializer {
                val retrofitRepository = (this[ViewModelProvider.AndroidViewModelFactory.APPLICATION_KEY] as MyApplication).retrofitRepo
                TestViewModel(
                    repo = retrofitRepository,
                )
            }
        }
    }

}