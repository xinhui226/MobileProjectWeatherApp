package com.xinhui.mobileprojectweatherapp.ui.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.xinhui.mobileprojectweatherapp.databinding.EditDeleteWeatherBinding
import com.xinhui.mobileprojectweatherapp.ui.viewModels.EditDeleteViewModel

class EditDeleteFragment : Fragment() {

    private lateinit var viewModel: EditDeleteViewModel
    private lateinit var binding:EditDeleteWeatherBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = EditDeleteWeatherBinding.inflate(inflater,container,false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.run {
            editDeleteVM = viewModel
            lifecycleOwner = lifecycleOwner
        }
    }

}