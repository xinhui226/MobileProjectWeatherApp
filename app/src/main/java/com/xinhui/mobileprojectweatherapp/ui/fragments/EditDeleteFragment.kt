package com.xinhui.mobileprojectweatherapp.ui.fragments

import android.os.Build
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.annotation.RequiresApi
import androidx.fragment.app.viewModels
import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import com.xinhui.mobileprojectweatherapp.data.repository.LocationRepo
import com.xinhui.mobileprojectweatherapp.databinding.EditDeleteWeatherBinding
import com.xinhui.mobileprojectweatherapp.ui.adapters.EditDeleteWeatherAdapter
import com.xinhui.mobileprojectweatherapp.ui.util.Constant.TAG
import com.xinhui.mobileprojectweatherapp.ui.viewModels.EditDeleteViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch

@RequiresApi(Build.VERSION_CODES.O)
class EditDeleteFragment : Fragment() {

    val viewModel: EditDeleteViewModel by viewModels {
        EditDeleteViewModel.Factory
    }

    private lateinit var binding:EditDeleteWeatherBinding
    private lateinit var adapter:EditDeleteWeatherAdapter

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

//        viewModel.currentWeather.asLiveData().observe(viewLifecycleOwner) {
//            Log.d(TAG, it.toString())
//        }
//        viewModel.forecastWeather.asLiveData().observe(viewLifecycleOwner){
//            viewModel.forecastWeather.value.map {
//                Log.d(TAG,  it.toString())
//            }
//        }

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
        binding.rvEditdltWeather.layoutManager = LinearLayoutManager(requireContext())
    }

}