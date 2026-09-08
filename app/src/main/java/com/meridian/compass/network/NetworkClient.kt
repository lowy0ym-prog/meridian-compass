package com.meridian.compass.network

import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor

/**
 * Network client for geocoding and location search.
 * Uses OpenStreetMap's Nominatim API (free, no API key required).
 */
object NetworkClient {
    private const val NOMINATIM_BASE_URL = "https://nominatim.openstreetmap.org/"

    private val httpClient = OkHttpClient.Builder()
        .addInterceptor(HttpLoggingInterceptor().apply {
            level = HttpLoggingInterceptor.Level.BASIC
        })
        .addHeader("User-Agent", "MeridianCompass/1.0.0")
        .build()

    fun getOkHttpClient(): OkHttpClient = httpClient

    const val getNominatimBaseUrl: String
        get() = NOMINATIM_BASE_URL
}
