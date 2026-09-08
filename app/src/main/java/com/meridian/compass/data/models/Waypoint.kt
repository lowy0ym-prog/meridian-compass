package com.meridian.compass.data.models

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "waypoint")
data class Waypoint(
    @PrimaryKey(autoGenerate = true)
    val id: Long = 0,
    val name: String,
    val latitude: Double,
    val longitude: Double,
    val createdAt: Long = System.currentTimeMillis(),
    val notes: String = "",
    val category: String = "default"
)
