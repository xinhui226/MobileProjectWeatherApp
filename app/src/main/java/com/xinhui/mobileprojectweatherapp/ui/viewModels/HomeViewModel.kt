package com.xinhui.mobileprojectweatherapp.ui.viewModels

import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelProvider.AndroidViewModelFactory.Companion.APPLICATION_KEY
import androidx.lifecycle.viewmodel.initializer
import androidx.lifecycle.viewmodel.viewModelFactory
import com.xinhui.mobileprojectweatherapp.MyApplication
import com.xinhui.mobileprojectweatherapp.data.repository.RetrofitRepo

class HomeViewModel(
    repo: RetrofitRepo
) : BaseLocationViewModel(repo) {


    companion object {
        val Factory: ViewModelProvider.Factory = viewModelFactory {
            initializer {
                val retrofitRepository =
                    (this[APPLICATION_KEY] as MyApplication).retrofitRepo
                HomeViewModel(
                    repo = retrofitRepository,
                )
            }
        }
    }
}