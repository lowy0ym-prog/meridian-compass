package com.meridian.compass.utils

import kotlin.math.*

/**
 * Utility functions for distance and bearing calculations.
 */
object DistanceUtils {
    private const val EARTH_RADIUS_KM = 6371.0
    private const val EARTH_RADIUS_MILES = 3959.0
    private const val FEET_PER_MILE = 5280.0
    private const val METERS_PER_MILE = 1609.34

    /**
     * Calculate distance between two points using Haversine formula.
     * Returns distance as a formatted string (feet for short distances, miles for long).
     */
    fun calculateDistance(
        lat1: Double,
        lon1: Double,
        lat2: Double,
        lon2: Double
    ): String {
        val distanceKm = calculateDistanceKm(lat1, lon1, lat2, lon2)
        val distanceMiles = distanceKm / 1.60934
        val distanceFeet = distanceMiles * FEET_PER_MILE

        return when {
            distanceFeet < 1000 -> String.format("%.0f ft", distanceFeet)
            distanceMiles < 10 -> String.format("%.1f mi", distanceMiles)
            else -> String.format("%.1f mi", distanceMiles)
        }
    }

    /**
     * Calculate distance in kilometers.
     */
    private fun calculateDistanceKm(
        lat1: Double,
        lon1: Double,
        lat2: Double,
        lon2: Double
    ): Double {
        val dLat = Math.toRadians(lat2 - lat1)
        val dLon = Math.toRadians(lon2 - lon1)
        val a = sin(dLat / 2) * sin(dLat / 2) +
                cos(Math.toRadians(lat1)) * cos(Math.toRadians(lat2)) *
                sin(dLon / 2) * sin(dLon / 2)
        val c = 2 * atan2(sqrt(a), sqrt(1 - a))
        return EARTH_RADIUS_KM * c
    }

    /**
     * Calculate bearing (direction) from point1 to point2.
     * Returns bearing in degrees (0-360).
     */
    fun calculateBearing(
        lat1: Double,
        lon1: Double,
        lat2: Double,
        lon2: Double
    ): Double {
        val dLon = Math.toRadians(lon2 - lon1)
        val lat1Rad = Math.toRadians(lat1)
        val lat2Rad = Math.toRadians(lat2)

        val y = sin(dLon) * cos(lat2Rad)
        val x = cos(lat1Rad) * sin(lat2Rad) -
                sin(lat1Rad) * cos(lat2Rad) * cos(dLon)
        val bearing = Math.toDegrees(atan2(y, x))
        return (bearing + 360) % 360
    }
}
