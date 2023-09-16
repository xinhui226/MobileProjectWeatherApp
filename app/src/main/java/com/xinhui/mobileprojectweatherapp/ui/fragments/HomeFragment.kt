package com.xinhui.mobileprojectweatherapp.ui.fragments

import androidx.fragment.app.viewModels
import com.xinhui.mobileprojectweatherapp.ui.viewModels.HomeViewModel


class HomeFragment : BaseLocationFragment() {
    override val viewModel: HomeViewModel by viewModels {
        HomeViewModel.Factory
    }
}

