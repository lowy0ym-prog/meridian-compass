package com.meridian.compass.data.db

import androidx.room.Database
import androidx.room.RoomDatabase
import com.meridian.compass.data.models.Trail
import com.meridian.compass.data.models.TrailPoint
import com.meridian.compass.data.models.Waypoint

@Database(
    entities = [Waypoint::class, Trail::class, TrailPoint::class],
    version = 1,
    exportSchema = false
)
abstract class MeridianDatabase : RoomDatabase() {
    abstract fun waypointDao(): WaypointDao
    abstract fun trailDao(): TrailDao
}
