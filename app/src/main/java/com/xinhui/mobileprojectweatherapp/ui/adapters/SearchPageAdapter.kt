package com.xinhui.mobileprojectweatherapp.ui.adapters

import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.xinhui.mobileprojectweatherapp.data.model.Location
import com.xinhui.mobileprojectweatherapp.databinding.ItemLayoutWeatherBinding
import com.xinhui.mobileprojectweatherapp.ui.util.Constant
import com.xinhui.mobileprojectweatherapp.ui.util.update

class SearchPageAdapter(
    private var locations:List<Location>,
): RecyclerView.Adapter<SearchPageAdapter.WeatherViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): WeatherViewHolder {
        val binding = ItemLayoutWeatherBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return WeatherViewHolder(binding)
    }

    override fun getItemCount(): Int = locations.size

    override fun onBindViewHolder(holder: WeatherViewHolder, position: Int) {
        val location = locations[position]
        Log.d(Constant.TAG, "onBindViewHolder: ${location.hashCode()}")
        holder.vbinding.run {
            tvTemp.text = location.temp
            tvWeatherDesc.text = location.weatherDesc
            tvLocation.text = location.city
        }
    }

    fun setLocation(location: List<Location>){
        Log.d(Constant.TAG, "setLocation: $location")
        val prev = this.locations
        this.locations = location
        update(prev,this.locations){ first,sec ->
            first.id == sec.id
        }
    }

    class WeatherViewHolder(val vbinding: ItemLayoutWeatherBinding):RecyclerView.ViewHolder(vbinding.root)

}