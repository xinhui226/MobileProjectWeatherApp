package com.xinhui.mobileprojectweatherapp.ui.util

import android.os.Build
import androidx.annotation.RequiresApi
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import java.time.LocalDateTime
import java.time.ZoneId
import java.time.format.DateTimeFormatter

object Constant {
    val TAG = "debugging"
}

@RequiresApi(Build.VERSION_CODES.O)
fun localtimeFormatter(timezone:String, timestamp:String):String{
    val formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm")
    return LocalDateTime.parse(timestamp, formatter).atZone(ZoneId.of("UTC")).withZoneSameInstant(ZoneId.of(timezone)).format(formatter)
}

fun <T> RecyclerView.Adapter<*>.update(
    prevList:List<T>,newList: List<T>,compare:(T,T)->Boolean
){
        val callback = object: DiffUtil.Callback(){
            override fun getOldListSize(): Int =prevList.size

            override fun getNewListSize(): Int =newList.size

            override fun areItemsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean {
                return compare(prevList[oldItemPosition],newList[newItemPosition])
            }

            override fun areContentsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean {
                return prevList[oldItemPosition] == newList[newItemPosition]
            }

        }
        val diff = DiffUtil.calculateDiff(callback)

        diff.dispatchUpdatesTo(this)
}