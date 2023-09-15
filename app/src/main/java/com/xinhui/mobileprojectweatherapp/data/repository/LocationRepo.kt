package com.xinhui.mobileprojectweatherapp.data.repository

import com.xinhui.mobileprojectweatherapp.data.db.LocationDao
import com.xinhui.mobileprojectweatherapp.data.model.Location
import kotlinx.coroutines.flow.Flow

class LocationRepo(private val dao: LocationDao) {

    fun getLocations():Flow<List<Location>>{
        return dao.getLocations()
    }

    fun getLocationById(id:Int):Location?{
        return dao.getLocationById(id)
    }

    fun updateLocationPriority(id:Int, priority:Int){
        return dao.updatePriorityById(id,priority)
    }

    fun savedLocation(location: Location){
        val id = dao.savedLocation(location)
        id?.let {
            dao.updatePriorityById(it.toInt(), it.toInt())
        }
    }

    fun removeSavedLocation(location: Location){
        dao.removeSavedLocation(location)
    }
}