package com.xinhui.mobileprojectweatherapp.ui.adapters

import android.view.LayoutInflater
import android.view.MotionEvent
import android.view.View
import android.view.View.OnTouchListener
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.xinhui.mobileprojectweatherapp.data.model.Location
import com.xinhui.mobileprojectweatherapp.databinding.ItemLayoutEditdelWeatherBinding
import com.xinhui.mobileprojectweatherapp.ui.util.ItemMoveCallback
import com.xinhui.mobileprojectweatherapp.ui.util.StartDragListener
import com.xinhui.mobileprojectweatherapp.ui.util.update
import java.util.Collections


class EditDeleteWeatherAdapter(
    private var locations:List<Location>,
    private val mStartDragListener:StartDragListener,
    private val onDragUpDown:(Int,Int)->Unit,
    private val onDeleteClick:(Location)->Unit,
):RecyclerView.Adapter<EditDeleteWeatherAdapter.WeatherViewHolder>(), ItemMoveCallback.ItemTouchHelperContract  {

    var shouldUpdate = true
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): WeatherViewHolder {
        val binding = ItemLayoutEditdelWeatherBinding.inflate(LayoutInflater.from(parent.context),parent,false)
        return WeatherViewHolder(binding)
    }

    override fun getItemCount(): Int = locations.size

    override fun onBindViewHolder(holder: WeatherViewHolder, position: Int) {
        val location = locations[position]
        holder.vbinding.run {
            tvTemp.text = location.temp
            tvLastUpd.text = location.localtime
            tvWeatherDesc.text = location.weatherDesc
            tvLocation.text = location.city
            ivDelete.setOnClickListener {
                shouldUpdate = true
                onDeleteClick(location)
            }

            if(position == 0){
                ivDelete.visibility = View.GONE
                ivDrag.visibility = View.GONE
            }
        }

        holder.vbinding.ivDrag.setOnTouchListener(OnTouchListener { v, event ->
            if (event.action ==
                MotionEvent.ACTION_DOWN
            ) {
                mStartDragListener.requestDrag(holder)
            }
            false
        })
    }

    fun setLocation(locations: List<Location>){
        val prev = this.locations
        this.locations = locations
        if(shouldUpdate){
            update(prev,this.locations){ first,sec ->
                first.priority == sec.priority
            }
        }
    }

    override fun onRowMoved(fromPosition: Int, toPosition: Int) {
        if(toPosition != 0){
            onDragUpDown(fromPosition, toPosition)
            Collections.swap(locations, fromPosition, toPosition)
            notifyItemMoved(fromPosition, toPosition)
        }
    }

    override fun onRowSelected(myViewHolder: WeatherViewHolder?) {
        shouldUpdate = false
    }

    override fun onRowClear(myViewHolder: WeatherViewHolder?) {
        shouldUpdate = false
    }

    class WeatherViewHolder(val vbinding:ItemLayoutEditdelWeatherBinding):RecyclerView.ViewHolder(vbinding.root)

}