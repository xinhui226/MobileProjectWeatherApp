package com.xinhui.mobileprojectweatherapp.adapter

import android.os.Build
import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.annotation.RequiresApi
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.xinhui.mobileprojectweatherapp.data.model.ForecastWeatherDisplay
import com.xinhui.mobileprojectweatherapp.databinding.HorizontalRvLayoutBinding
import java.time.format.DateTimeFormatter


class HorizontalRVAdapter(var forecast: List<ForecastWeatherDisplay>) : RecyclerView.Adapter<RecyclerView.ViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        val binding = HorizontalRvLayoutBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ForecastViewHolder(binding)
    }

    override fun getItemCount(): Int {
        return forecast.size
    }

    @RequiresApi(Build.VERSION_CODES.O)
    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        val forecastWeather = forecast[position]
        val baseIconURL = "https://cdn.weatherbit.io/static/img/icons/"

        if(holder is ForecastViewHolder) {
            holder.binding.run{
                tvShowWeatherTempInHSV.text = forecastWeather.temp

                val iconURL = "$baseIconURL${forecastWeather.weather.icon}.png"
                Glide
                    .with(holder.itemView)
                    .load(iconURL)
                    .into(ivShowIconInHSV)

                tvShowTimeInHSV.text = forecastWeather.timeStamp.dropLast(3)
            }
        }
    }

    class ForecastViewHolder(val binding: HorizontalRvLayoutBinding) : RecyclerView.ViewHolder(binding.root)

}