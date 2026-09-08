package com.meridian.compass.data.models

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "trails")
data class Trail(
    @PrimaryKey(autoGenerate = true)
    val id: Int = 0,
    val name: String,
    val startedAt: Long = System.currentTimeMillis(),
    val endedAt: Long? = null,
    val totalDistance: Double = 0.0,
    val totalDuration: Long = 0,
    val isRecording: Boolean = false
)

@Entity(tableName = "trail_points")
data class TrailPoint(
    @PrimaryKey(autoGenerate = true)
    val id: Int = 0,
    val trailId: Int,
    val latitude: Double,
    val longitude: Double,
    val altitude: Double? = null,
    val accuracy: Float? = null,
    val timestamp: Long = System.currentTimeMillis()
)
