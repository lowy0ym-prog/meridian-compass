package com.meridian.compass.data.db

import androidx.room.*
import com.meridian.compass.data.models.Trail
import kotlinx.coroutines.flow.Flow

@Dao
interface TrailDao {
    @Query("SELECT * FROM trail")
    fun getAllTrails(): Flow<List<Trail>>

    @Query("SELECT * FROM trail WHERE id = :id")
    suspend fun getTrail(id: Long): Trail?

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertTrail(trail: Trail): Long

    @Update
    suspend fun updateTrail(trail: Trail)

    @Delete
    suspend fun deleteTrail(trail: Trail)
}
