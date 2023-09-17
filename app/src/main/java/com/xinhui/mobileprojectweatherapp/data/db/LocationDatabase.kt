package com.xinhui.mobileprojectweatherapp.data.db

import androidx.room.Database
import androidx.room.RoomDatabase
import com.xinhui.mobileprojectweatherapp.data.model.Location

@Database(entities = [Location::class], version = 10)
abstract class LocationDatabase():RoomDatabase() {
    abstract val dao:LocationDao

    companion object{
        const val dbName = "location_database"
    }
}