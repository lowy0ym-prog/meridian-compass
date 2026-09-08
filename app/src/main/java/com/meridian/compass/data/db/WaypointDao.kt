package com.meridian.compass.data.db

import androidx.room.*
import com.meridian.compass.data.models.Waypoint
import kotlinx.coroutines.flow.Flow

@Dao
interface WaypointDao {
    @Query("SELECT * FROM waypoint")
    fun getAllWaypoints(): Flow<List<Waypoint>>

    @Query("SELECT * FROM waypoint WHERE id = :id")
    suspend fun getWaypoint(id: Long): Waypoint?

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertWaypoint(waypoint: Waypoint): Long

    @Update
    suspend fun updateWaypoint(waypoint: Waypoint)

    @Delete
    suspend fun deleteWaypoint(waypoint: Waypoint)

    @Query("DELETE FROM waypoint")
    suspend fun deleteAllWaypoints()
}
