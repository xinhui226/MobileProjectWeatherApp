package com.xinhui.mobileprojectweatherapp.ui.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.navigation.NavController
import androidx.navigation.fragment.NavHostFragment
import androidx.recyclerview.widget.LinearLayoutManager
import com.xinhui.mobileprojectweatherapp.databinding.EditDeleteWeatherBinding
import com.xinhui.mobileprojectweatherapp.ui.adapters.EditDeleteWeatherAdapter
import com.xinhui.mobileprojectweatherapp.ui.viewModels.EditDeleteViewModel
import kotlinx.coroutines.launch

class EditDeleteFragment : Fragment() {

    private lateinit var binding:EditDeleteWeatherBinding
    private lateinit var adapter:EditDeleteWeatherAdapter
    private lateinit var navController: NavController
    private val viewModel: EditDeleteViewModel by viewModels {
        EditDeleteViewModel.Factory
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = EditDeleteWeatherBinding.inflate(
            inflater,
            container,
            false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        navController = NavHostFragment.findNavController(this)

        binding.run {
            editDeleteVM = viewModel
            lifecycleOwner = lifecycleOwner
            tvDone.setOnClickListener {
                navController.popBackStack()
            }
        }

        setupAdapter()

        lifecycleScope.launch{
            viewModel.getLocations().collect{
                adapter
                    .setLocation(it)
            }
        }

    }

    fun setupAdapter(){
        adapter = EditDeleteWeatherAdapter(emptyList()){
            viewModel.deleteLocation(it)
        }

        binding.rvEditdltWeather.adapter = adapter
        binding.rvEditdltWeather.layoutManager =
            LinearLayoutManager(requireContext())
    }

}