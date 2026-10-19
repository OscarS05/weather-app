package com.example.weatherapp.data.local

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query

@Dao
interface CityDao {

    @Query("SELECT * FROM cities WHERE isCurrentLocation = 1 LIMIT 1")
    suspend fun getCurrentCity(): CityEntity?

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insert(city: CityEntity): Long

    @Query("SELECT * FROM cities ORDER BY name ASC")
    suspend fun getAllCities(): List<CityEntity>
}