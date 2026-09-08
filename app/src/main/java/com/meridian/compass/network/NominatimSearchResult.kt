package com.meridian.compass.network

import com.squareup.moshi.Json

/**
 * Response from Nominatim search API.
 */
data class NominatimSearchResult(
    @Json(name = "lat")
    val latitude: String,
    @Json(name = "lon")
    val longitude: String,
    @Json(name = "display_name")
    val displayName: String,
    @Json(name = "type")
    val type: String
)
