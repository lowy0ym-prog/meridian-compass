package com.meridian.compass.data.models

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "waypoints")
data class Waypoint(
    @PrimaryKey(autoGenerate = true)
    val id: Int = 0,
    val name: String,
    val latitude: Double,
    val longitude: Double,
    val category: String = "DEFAULT",
    val createdAt: Long = System.currentTimeMillis(),
    val notes: String = "",
    val photoPath: String? = null
)

object WaypointCategories {
    const val CAMPSITE = "CAMPSITE"
    const val TRAIL = "TRAIL"
    const val HIKING = "HIKING"
    const val MOUNTAIN = "MOUNTAIN"
    const val FISHING = "FISHING"
    const val SCENIC = "SCENIC"
    const val CAR = "CAR"
    const val PARKING = "PARKING"
    const val GAS_STATION = "GAS_STATION"
    const val BOAT = "BOAT"
    const val RESTAURANT = "RESTAURANT"
    const val FOOD = "FOOD"
    const val WATER = "WATER"
    const val HOME = "HOME"
    const val HOTEL = "HOTEL"
    const val BUILDING = "BUILDING"
    const val ENTRANCE = "ENTRANCE"
    const val EVENT = "EVENT"
    const val STAR = "STAR"
    const val IMPORTANT = "IMPORTANT"
    const val DEFAULT = "DEFAULT"

    fun getDisplay(category: String): String = when (category) {
        CAMPSITE -> "Campsite"
        TRAIL -> "Trail"
        HIKING -> "Hiking"
        MOUNTAIN -> "Mountain"
        FISHING -> "Fishing"
        SCENIC -> "Scenic Location"
        CAR -> "Car"
        PARKING -> "Parking"
        GAS_STATION -> "Gas Station"
        BOAT -> "Boat"
        RESTAURANT -> "Restaurant"
        FOOD -> "Food"
        WATER -> "Water"
        HOME -> "Home"
        HOTEL -> "Hotel"
        BUILDING -> "Building"
        ENTRANCE -> "Entrance"
        EVENT -> "Event"
        STAR -> "Star"
        IMPORTANT -> "Important"
        else -> "Location"
    }

    fun getEmoji(category: String): String = when (category) {
        CAMPSITE -> "🏕️"
        TRAIL -> "🥾"
        HIKING -> "⛰️"
        MOUNTAIN -> "🏔️"
        FISHING -> "🎣"
        SCENIC -> "🌄"
        CAR -> "🚗"
        PARKING -> "🅿️"
        GAS_STATION -> "⛽"
        BOAT -> "⛵"
        RESTAURANT -> "🍽️"
        FOOD -> "🍲"
        WATER -> "💧"
        HOME -> "🏠"
        HOTEL -> "🏨"
        BUILDING -> "🏢"
        ENTRANCE -> "🚪"
        EVENT -> "🎉"
        STAR -> "⭐"
        IMPORTANT -> "⚠️"
        else -> "📍"
    }
}
