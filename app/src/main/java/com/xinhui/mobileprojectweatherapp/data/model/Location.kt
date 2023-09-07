package com.xinhui.mobileprojectweatherapp.data.model

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class Location(
    @PrimaryKey(autoGenerate = true)
    val id:Int? = null,
    val location:String
)