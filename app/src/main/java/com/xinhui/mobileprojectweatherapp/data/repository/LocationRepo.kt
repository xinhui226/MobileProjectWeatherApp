package com.xinhui.mobileprojectweatherapp.data.repository

import com.xinhui.mobileprojectweatherapp.data.db.LocationDao
import com.xinhui.mobileprojectweatherapp.data.model.Location
import kotlinx.coroutines.flow.Flow

class LocationRepo(private val dao: LocationDao) {

    fun getLocations():Flow<List<Location>>{
        return dao.getLocations()
    }

    fun getLocationById(id:Int):Location?{
        return dao.getLocationsById(id)
    }

    fun savedLocation(location: Location){
        dao.savedLocation(location)
    }

    fun removeSavedLocation(location: Location){
        dao.removeSavedLocation(location)
    }
}