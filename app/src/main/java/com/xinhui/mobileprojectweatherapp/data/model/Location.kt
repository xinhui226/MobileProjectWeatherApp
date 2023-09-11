package com.xinhui.mobileprojectweatherapp.data.model

import androidx.room.Entity
import androidx.room.Index
import androidx.room.PrimaryKey

@Entity(indices = [Index(value = ["city"],
    unique = true)])
data class Location(
    @PrimaryKey(autoGenerate = true)
    val id:Int? = null,
    val city:String,
    val temp:String,
    val localtime:String,
    val weatherDesc:String,
)