package com.xinhui.mobileprojectweatherapp.ui.fragments

import android.os.Bundle
import android.view.View
import androidx.core.view.isVisible
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.NavHostFragment
import com.xinhui.mobileprojectweatherapp.ui.viewModels.HomeViewModel


class HomeFragment : BaseLocationFragment() {
    override val viewModel: HomeViewModel by viewModels {
        HomeViewModel.Factory
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        navController = NavHostFragment.findNavController(this)

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