package com.meridian.compass.data.repositories

import com.meridian.compass.data.db.TrailDao
import com.meridian.compass.data.models.Trail
import kotlinx.coroutines.flow.Flow

class TrailRepository(private val trailDao: TrailDao) {
    val allTrails: Flow<List<Trail>> = trailDao.getAllTrails()

    suspend fun getTrail(id: Long): Trail? = trailDao.getTrail(id)

    suspend fun insert(trail: Trail): Long = trailDao.insertTrail(trail)

    suspend fun update(trail: Trail) = trailDao.updateTrail(trail)

    suspend fun delete(trail: Trail) = trailDao.deleteTrail(trail)
}
