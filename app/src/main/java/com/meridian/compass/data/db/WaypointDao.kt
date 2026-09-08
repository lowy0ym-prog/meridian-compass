package com.meridian.compass.data.db

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Update
import com.meridian.compass.data.models.Waypoint
import kotlinx.coroutines.flow.Flow

@Dao
interface WaypointDao {
    @Insert
    suspend fun insert(waypoint: Waypoint): Long

    @Update
    suspend fun update(waypoint: Waypoint)

    @Delete
    suspend fun delete(waypoint: Waypoint)

    @Query("SELECT * FROM waypoints WHERE id = :id")
    suspend fun getById(id: Int): Waypoint?

    @Query("SELECT * FROM waypoints ORDER BY createdAt DESC")
    fun getAllFlow(): Flow<List<Waypoint>>

    @Query("SELECT * FROM waypoints ORDER BY createdAt DESC")
    suspend fun getAll(): List<Waypoint>

    @Query("SELECT * FROM waypoints WHERE category = :category ORDER BY createdAt DESC")
    fun getByCategory(category: String): Flow<List<Waypoint>>

    @Query("DELETE FROM waypoints")
    suspend fun deleteAll()
}
