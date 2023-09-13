package com.xinhui.mobileprojectweatherapp.ui.fragments

import android.os.Build
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.annotation.RequiresApi
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.ItemTouchHelper
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.xinhui.mobileprojectweatherapp.databinding.EditDeleteWeatherBinding
import com.xinhui.mobileprojectweatherapp.ui.util.ItemMoveCallback
import com.xinhui.mobileprojectweatherapp.ui.adapters.EditDeleteWeatherAdapter
import com.xinhui.mobileprojectweatherapp.ui.util.Constant.TAG
import com.xinhui.mobileprojectweatherapp.ui.util.StartDragListener
import com.xinhui.mobileprojectweatherapp.ui.viewModels.EditDeleteViewModel
import kotlinx.coroutines.launch


@RequiresApi(Build.VERSION_CODES.O)
class EditDeleteFragment : Fragment(),StartDragListener {

    val viewModel: EditDeleteViewModel by viewModels {
        EditDeleteViewModel.Factory
    }

    private lateinit var binding:EditDeleteWeatherBinding
    private lateinit var adapter:EditDeleteWeatherAdapter
    private lateinit var touchHelper:ItemTouchHelper

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

            tvDone.setOnClickListener { viewModel.addLocation() }
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
                Log.d(TAG, "onViewCreated: $it")
                adapter
                    .setLocation(it)
            }
        }

    }

    fun setupAdapter(){
        adapter = EditDeleteWeatherAdapter(emptyList(),this,
            {from,to ->
                viewModel.updLocationPrio(from, to)
            }){
            viewModel.deleteLocation(it)
        }

        val callback: ItemTouchHelper.Callback = ItemMoveCallback(adapter)
        touchHelper = ItemTouchHelper(callback)
        touchHelper.attachToRecyclerView(binding.rvEditdltWeather)

        binding.rvEditdltWeather.adapter = adapter
        binding.rvEditdltWeather.layoutManager = LinearLayoutManager(requireContext())
    }

    override fun requestDrag(viewHolder: RecyclerView.ViewHolder) {
            touchHelper.startDrag(viewHolder)
    }

}