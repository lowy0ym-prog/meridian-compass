package com.meridian.compass.data.repository

import com.meridian.compass.data.db.TrailDao
import com.meridian.compass.data.models.Trail
import com.meridian.compass.data.models.TrailPoint
import kotlinx.coroutines.flow.Flow

class TrailRepository(private val dao: TrailDao) {
    fun getAllTrails(): Flow<List<Trail>> = dao.getAllTrailsFlow()

    suspend fun getTrail(id: Int): Trail? = dao.getTrailById(id)

    suspend fun getTrailPoints(trailId: Int): List<TrailPoint> = dao.getTrailPoints(trailId)

    fun getTrailPointsFlow(trailId: Int): Flow<List<TrailPoint>> = dao.getTrailPointsFlow(trailId)

    suspend fun createTrail(trail: Trail): Long = dao.insertTrail(trail)

    suspend fun updateTrail(trail: Trail) = dao.updateTrail(trail)

    suspend fun addTrailPoint(point: TrailPoint) = dao.insertPoint(point)

    suspend fun deleteTrail(trail: Trail) {
        dao.deletePoints(trail.id)
        dao.deleteTrail(trail)
    }

    suspend fun deleteAllTrails() {
        dao.deleteAllPoints()
        dao.deleteAllTrails()
    }
}
