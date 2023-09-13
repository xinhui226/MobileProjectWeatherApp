package com.xinhui.mobileprojectweatherapp.ui.util
import androidx.recyclerview.widget.RecyclerView


interface StartDragListener {
    fun requestDrag(viewHolder: RecyclerView.ViewHolder)
}