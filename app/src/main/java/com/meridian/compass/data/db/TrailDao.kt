package com.meridian.compass.data.db

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Update
import com.meridian.compass.data.models.Trail
import com.meridian.compass.data.models.TrailPoint
import kotlinx.coroutines.flow.Flow

@Dao
interface TrailDao {
    @Insert
    suspend fun insertTrail(trail: Trail): Long

    @Insert
    suspend fun insertPoint(point: TrailPoint)

    @Update
    suspend fun updateTrail(trail: Trail)

    @Delete
    suspend fun deleteTrail(trail: Trail)

    @Query("DELETE FROM trail_points WHERE trailId = :trailId")
    suspend fun deletePoints(trailId: Int)

    @Query("SELECT * FROM trails ORDER BY startedAt DESC")
    fun getAllTrailsFlow(): Flow<List<Trail>>

    @Query("SELECT * FROM trails ORDER BY startedAt DESC")
    suspend fun getAllTrails(): List<Trail>

    @Query("SELECT * FROM trails WHERE id = :id")
    suspend fun getTrailById(id: Int): Trail?

    @Query("SELECT * FROM trail_points WHERE trailId = :trailId ORDER BY timestamp ASC")
    suspend fun getTrailPoints(trailId: Int): List<TrailPoint>

    @Query("SELECT * FROM trail_points WHERE trailId = :trailId ORDER BY timestamp ASC")
    fun getTrailPointsFlow(trailId: Int): Flow<List<TrailPoint>>

    @Query("DELETE FROM trails")
    suspend fun deleteAllTrails()

    @Query("DELETE FROM trail_points")
    suspend fun deleteAllPoints()
}
