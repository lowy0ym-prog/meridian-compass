package com.meridian.compass.map

/**
 * Centralized map configuration.
 * Change these values to use a different map tile provider.
 */
object MapConfig {
    // OpenStreetMap tiles (free, no API key required)
    const val TILE_URL = "https://tile.openstreetmap.org/{z}/{x}/{y}.png"
    
    // Alternative providers (uncomment to use):
    // Mapnik (Standard OSM): https://tile.openstreetmap.org/{z}/{x}/{y}.png
    // Stamen Toner: https://tile.stamen.com/toner/{z}/{x}/{y}.png
    // Stamen Toner Lite: https://tile.stamen.com/toner-lite/{z}/{x}/{y}.png
    // CartoDB Positron (light): https://cartodb-basemaps-a.global.ssl.fastly.net/light_all/{z}/{x}/{y}.png
    // CartoDB Voyager (dark): https://cartodb-basemaps-a.global.ssl.fastly.net/rastertiles/voyager/{z}/{x}/{y}.png
    
    const val MAP_STYLE_DARK = "https://demotiles.maplibre.org/style.json"
    const val MAP_STYLE_LIGHT = "https://demotiles.maplibre.org/style.json"
    
    const val DEFAULT_ZOOM = 15.0
    const val DEFAULT_LAT = 40.7128
    const val DEFAULT_LON = -74.0060
    
    const val MIN_ZOOM = 2.0
    const val MAX_ZOOM = 19.0
}
