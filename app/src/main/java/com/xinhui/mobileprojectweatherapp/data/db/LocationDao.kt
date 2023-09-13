package com.xinhui.mobileprojectweatherapp.data.db

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.xinhui.mobileprojectweatherapp.data.model.Location
import kotlinx.coroutines.flow.Flow

@Dao
interface LocationDao {

    @Query("SELECT * FROM location ORDER BY priority")
    fun getLocations(): Flow<List<Location>>

    @Query("SELECT * FROM location where id = :id")
    fun getLocationById(id:Int): Location?

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun savedLocation(location:Location): Long?

    @Delete
    fun removeSavedLocation(location: Location)

    @Query("UPDATE location SET priority = :priority WHERE id = :id")
    fun updatePriorityById(id:Int,priority: Int)
}