package com.xinhui.mobileprojectweatherapp.ui

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewModelScope
import androidx.lifecycle.viewmodel.initializer
import androidx.lifecycle.viewmodel.viewModelFactory
import com.xinhui.mobileprojectweatherapp.MyApplication
import com.xinhui.mobileprojectweatherapp.data.repository.RetrofitRepo
import com.xinhui.mobileprojectweatherapp.ui.util.Constant.TAG
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
class TestViewModel(
    private val repo: RetrofitRepo,
) :ViewModel(){

    fun test() {
        viewModelScope.launch(Dispatchers.IO) {
            try {
                val city = "Georgetown,MY"
                val res = repo.getForecastWeather(city)
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