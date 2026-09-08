package com.meridian.compass.location

import android.content.Context
import android.location.Location
import android.location.LocationListener
import android.location.LocationManager
import android.os.Bundle
import android.os.Looper
import com.meridian.compass.data.models.GpsLocation
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlin.math.cos
import kotlin.math.sin
import kotlin.math.sqrt

class GpsManager(private val context: Context) {
    private val locationManager = context.getSystemService(Context.LOCATION_SERVICE) as LocationManager

    private val _currentLocation = MutableStateFlow<GpsLocation?>(null)
    val currentLocation: StateFlow<GpsLocation?> = _currentLocation

    private val locationListener = object : LocationListener {
        override fun onLocationChanged(location: Location) {
            _currentLocation.value = GpsLocation(
                latitude = location.latitude,
                longitude = location.longitude,
                altitude = if (location.hasAltitude()) location.altitude else null,
                accuracy = if (location.hasAccuracy()) location.accuracy else null,
                bearing = if (location.hasBearing()) location.bearing else null,
                speed = if (location.hasSpeed()) location.speed else null,
                timestamp = location.time
            )
        }

        override fun onProviderEnabled(provider: String) {}
        override fun onProviderDisabled(provider: String) {}
        override fun onStatusChanged(provider: String?, status: Int, extras: Bundle?) {}
    }

    fun startLocationUpdates() {
        try {
            if (locationManager.isProviderEnabled(LocationManager.GPS_PROVIDER)) {
                locationManager.requestLocationUpdates(
                    LocationManager.GPS_PROVIDER,
                    1000, // 1 second
                    1f, // 1 meter
                    locationListener,
                    Looper.getMainLooper()
                )
            } else if (locationManager.isProviderEnabled(LocationManager.NETWORK_PROVIDER)) {
                locationManager.requestLocationUpdates(
                    LocationManager.NETWORK_PROVIDER,
                    1000,
                    1f,
                    locationListener,
                    Looper.getMainLooper()
                )
            }
        } catch (e: SecurityException) {
            e.printStackTrace()
        }
    }

    fun stopLocationUpdates() {
        try {
            locationManager.removeUpdates(locationListener)
        } catch (e: SecurityException) {
            e.printStackTrace()
        }
    }

    fun getLastKnownLocation(): GpsLocation? {
        return try {
            val location = locationManager.getLastKnownLocation(LocationManager.GPS_PROVIDER)
                ?: locationManager.getLastKnownLocation(LocationManager.NETWORK_PROVIDER)
            location?.let {
                GpsLocation(
                    latitude = it.latitude,
                    longitude = it.longitude,
                    altitude = if (it.hasAltitude()) it.altitude else null,
                    accuracy = if (it.hasAccuracy()) it.accuracy else null,
                    bearing = if (it.hasBearing()) it.bearing else null,
                    speed = if (it.hasSpeed()) it.speed else null,
                    timestamp = it.time
                )
            }
        } catch (e: SecurityException) {
            null
        }
    }
}

object DistanceCalculator {
    private const val EARTH_RADIUS_METERS = 6371000.0

    fun distanceBetween(lat1: Double, lon1: Double, lat2: Double, lon2: Double): Double {
        val dLat = Math.toRadians(lat2 - lat1)
        val dLon = Math.toRadians(lon2 - lon1)
        val a = sin(dLat / 2) * sin(dLat / 2) +
                cos(Math.toRadians(lat1)) * cos(Math.toRadians(lat2)) *
                sin(dLon / 2) * sin(dLon / 2)
        val c = 2 * kotlin.math.atan2(sqrt(a), sqrt(1 - a))
        return EARTH_RADIUS_METERS * c
    }

    fun bearingTo(lat1: Double, lon1: Double, lat2: Double, lon2: Double): Float {
        val dLon = Math.toRadians(lon2 - lon1)
        val y = sin(dLon) * cos(Math.toRadians(lat2))
        val x = cos(Math.toRadians(lat1)) * sin(Math.toRadians(lat2)) -
                sin(Math.toRadians(lat1)) * cos(Math.toRadians(lat2)) * cos(dLon)
        val bearing = Math.toDegrees(kotlin.math.atan2(y, x))
        return ((bearing + 360) % 360).toFloat()
    }
}
