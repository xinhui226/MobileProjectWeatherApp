package com.xinhui.mobileprojectweatherapp.ui.fragments

import android.os.Bundle
import android.view.View
import androidx.core.content.ContextCompat
import androidx.fragment.app.Fragment
import androidx.lifecycle.lifecycleScope
import com.google.android.material.snackbar.Snackbar
import com.xinhui.mobileprojectweatherapp.R
import com.xinhui.mobileprojectweatherapp.ui.viewModels.BaseViewModel
import kotlinx.coroutines.launch

abstract class BaseFragment : Fragment() {
    abstract val viewModel: BaseViewModel

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)


        lifecycleScope.launch {
            viewModel.error.collect {
                Snackbar.make(view, it, Snackbar.LENGTH_LONG)
                    .apply {
                        setBackgroundTint(
                            ContextCompat.getColor(requireContext(), R.color.red)
                        )
                    }
                    .show()
            }
        }
    }
}