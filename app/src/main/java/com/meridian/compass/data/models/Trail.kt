package com.meridian.compass.data.models

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "trail")
data class Trail(
    @PrimaryKey(autoGenerate = true)
    val id: Long = 0,
    val name: String,
    val startTime: Long = System.currentTimeMillis(),
    val endTime: Long? = null,
    val distanceMeters: Double = 0.0,
    val durationSeconds: Long = 0,
    val points: String = "" // JSON array of lat/lon pairs
)
