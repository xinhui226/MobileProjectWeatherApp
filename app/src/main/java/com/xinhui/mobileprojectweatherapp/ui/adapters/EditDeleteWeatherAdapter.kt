package com.xinhui.mobileprojectweatherapp.ui.adapters

import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.xinhui.mobileprojectweatherapp.data.model.Location
import com.xinhui.mobileprojectweatherapp.databinding.ItemLayoutEditdelWeatherBinding
import com.xinhui.mobileprojectweatherapp.ui.util.Constant.TAG
import com.xinhui.mobileprojectweatherapp.ui.util.update

class EditDeleteWeatherAdapter(
    private var locations:List<Location>,
    private val onDeleteClick:(Location)->Unit
):RecyclerView.Adapter<EditDeleteWeatherAdapter.WeatherViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): WeatherViewHolder {
        val binding = ItemLayoutEditdelWeatherBinding.inflate(LayoutInflater.from(parent.context),parent,false)
        return WeatherViewHolder(binding)
    }

    override fun getItemCount(): Int = locations.size

    override fun onBindViewHolder(holder: WeatherViewHolder, position: Int) {
        val location = locations[position]
        Log.d(TAG, "onBindViewHolder: ${location.hashCode()}")
        holder.vbinding.run {
            tvTemp.text = location.temp
            tvLastUpd.text = location.localtime
            tvWeatherDesc.text = location.weatherDesc
            tvLocation.text = location.city
            ivDelete.setOnClickListener { onDeleteClick(location) }
//            ivDrag
        }
    }

    fun setLocation(location: List<Location>){
        Log.d(TAG, "setLocation: $location")
//      notifyDataSetChanged()
        val prev = this.locations
        this.locations = location
        update(prev,this.locations){ first,sec ->
            first.id == sec.id
        }
    }

    class WeatherViewHolder(val vbinding:ItemLayoutEditdelWeatherBinding):RecyclerView.ViewHolder(vbinding.root)

}