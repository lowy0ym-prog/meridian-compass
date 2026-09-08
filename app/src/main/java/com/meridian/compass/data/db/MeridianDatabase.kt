package com.meridian.compass.data.db

import androidx.room.Database
import androidx.room.RoomDatabase
import com.meridian.compass.data.models.Waypoint
import com.meridian.compass.data.models.Trail

@Database(
    entities = [Waypoint::class, Trail::class],
    version = 1,
    exportSchema = false
)
abstract class MeridianDatabase : RoomDatabase() {
    abstract fun waypointDao(): WaypointDao
    abstract fun trailDao(): TrailDao
}
