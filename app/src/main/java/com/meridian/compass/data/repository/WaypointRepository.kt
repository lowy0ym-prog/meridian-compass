package com.meridian.compass.data.repository

import com.meridian.compass.data.db.WaypointDao
import com.meridian.compass.data.models.Waypoint
import kotlinx.coroutines.flow.Flow

class WaypointRepository(private val dao: WaypointDao) {
    fun getAllWaypoints(): Flow<List<Waypoint>> = dao.getAllFlow()

    fun getWaypointsByCategory(category: String): Flow<List<Waypoint>> = dao.getByCategory(category)

    suspend fun getWaypoint(id: Int): Waypoint? = dao.getById(id)

    suspend fun saveWaypoint(waypoint: Waypoint): Long = dao.insert(waypoint)

    suspend fun updateWaypoint(waypoint: Waypoint) = dao.update(waypoint)

    suspend fun deleteWaypoint(waypoint: Waypoint) = dao.delete(waypoint)

    suspend fun deleteAllWaypoints() = dao.deleteAll()
}
