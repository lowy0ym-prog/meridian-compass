package com.meridian.compass.data.repositories

import com.meridian.compass.data.db.WaypointDao
import com.meridian.compass.data.models.Waypoint
import kotlinx.coroutines.flow.Flow

class WaypointRepository(private val waypointDao: WaypointDao) {
    val allWaypoints: Flow<List<Waypoint>> = waypointDao.getAllWaypoints()

    suspend fun getWaypoint(id: Long): Waypoint? = waypointDao.getWaypoint(id)

    suspend fun insert(waypoint: Waypoint): Long = waypointDao.insertWaypoint(waypoint)

    suspend fun update(waypoint: Waypoint) = waypointDao.updateWaypoint(waypoint)

    suspend fun delete(waypoint: Waypoint) = waypointDao.deleteWaypoint(waypoint)

    suspend fun deleteAll() = waypointDao.deleteAllWaypoints()
}
