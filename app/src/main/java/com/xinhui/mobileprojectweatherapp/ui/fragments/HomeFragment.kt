package com.xinhui.mobileprojectweatherapp.ui.fragments

import android.os.Bundle
import android.view.View
import androidx.core.view.isVisible
import androidx.fragment.app.viewModels
import androidx.lifecycle.asLiveData
import androidx.navigation.fragment.NavHostFragment
import com.xinhui.mobileprojectweatherapp.ui.viewModels.HomeViewModel


class HomeFragment : BaseLocationFragment() {
    override val viewModel: HomeViewModel by viewModels {
        HomeViewModel.Factory
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        navController = NavHostFragment.findNavController(this)

        viewModel.finishLoading.asLiveData().observe(viewLifecycleOwner){
            binding.run {
                llLoading.visibility = View.GONE
                clShowWeather.visibility = View.VISIBLE
            }
        }

        binding.run {
            ivListItemMenu.isVisible = true
            tvCancel.isVisible = false
            tvSave.isVisible = false
            tvRemove.isVisible = false
            ivListItemMenu.setOnClickListener {
                val action = HomeFragmentDirections.actionHomeToSearchPage()
                navController.navigate(action)
            }
        }

    }
}