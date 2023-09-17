package com.xinhui.mobileprojectweatherapp.data.model

import androidx.room.Entity
import androidx.room.Index
import androidx.room.PrimaryKey

@Entity(indices = [Index(value = ["savecityname"],
    unique = true)])
data class Location(
    @PrimaryKey(autoGenerate = true)
    val id:Int? = null,
    val savecityname: String,
    val city:String,
    val temp:String,
    val localtime:String,
    val weatherDesc:String,
    val priority:Int = 0,
)