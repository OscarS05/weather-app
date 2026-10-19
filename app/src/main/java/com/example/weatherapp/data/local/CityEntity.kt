package com.example.weatherapp.data.local

import androidx.room.Entity
import androidx.room.Index
import androidx.room.PrimaryKey

@Entity(
    tableName = "cities",
    indices = [Index(value = ["name", "country"], unique = true)]
)
data class CityEntity(
    @PrimaryKey(autoGenerate = true)
    val id: Int = 0,
    val name: String,
    val country: String,
    val latitude: Double,
    val longitude: Double,
    val isCurrentLocation: Boolean = false
)