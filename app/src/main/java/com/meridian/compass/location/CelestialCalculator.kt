package com.meridian.compass.location

import java.time.LocalDateTime
import java.time.ZoneId
import java.time.ZonedDateTime
import kotlin.math.acos
import kotlin.math.asin
import kotlin.math.atan2
import kotlin.math.cos
import kotlin.math.sin

data class SunPosition(val azimuth: Float, val altitude: Float)
data class SunTimes(val sunrise: LocalDateTime, val sunset: LocalDateTime)

data class MoonPosition(val azimuth: Float, val altitude: Float)
data class MoonTimes(val moonrise: LocalDateTime, val moonset: LocalDateTime, val phase: Float)

class CelestialCalculator {
    fun calculateSunPosition(
        latitude: Double,
        longitude: Double,
        dateTime: LocalDateTime = LocalDateTime.now()
    ): SunPosition {
        val zdt = dateTime.atZone(ZoneId.systemDefault())
        val julianDay = toJulianDay(zdt)
        val decimalHours = dateTime.hour + dateTime.minute / 60.0 + dateTime.second / 3600.0
        val julianCentury = (julianDay - 2451545.0) / 36525.0

        val geomSunLongDeg = (280.46646 + julianCentury * (36000.76983 + julianCentury * 0.0003032)) % 360
        val meanSunAnomaly = 357.52911 + julianCentury * (35999.05029 - 0.0001536 * julianCentury)
        val meanSunAnomRad = Math.toRadians(meanSunAnomaly)

        val sunEqOfCtr = (1.914602 - julianCentury * (0.004817 + 0.000014 * julianCentury)) * sin(meanSunAnomRad) +
                (0.019993 - 0.000101 * julianCentury) * sin(2 * meanSunAnomRad) +
                0.000029 * sin(3 * meanSunAnomRad)

        val sunTrueLong = geomSunLongDeg + sunEqOfCtr
        val sunAppLongDeg = sunTrueLong - 0.00569 - 0.00478 * sin(Math.toRadians(125.04 - 1934.136 * julianCentury))
        val meanObliquityDeg = 23.0 + 26.0 / 60.0 + 21.448 / 3600.0 -
                46.8150 / 3600.0 * julianCentury -
                0.00059 / 3600.0 * julianCentury * julianCentury +
                0.001813 / 3600.0 * julianCentury * julianCentury * julianCentury

        val obliquityCorr = meanObliquityDeg + 0.00256 * cos(Math.toRadians(125.04 - 1934.136 * julianCentury))
        val sunRtAscDeg = Math.toDegrees(
            atan2(
                cos(Math.toRadians(obliquityCorr)) * sin(Math.toRadians(sunAppLongDeg)),
                cos(Math.toRadians(sunAppLongDeg))
            )
        ) % 360

        val sunDeclin = Math.toDegrees(
            asin(sin(Math.toRadians(obliquityCorr)) * sin(Math.toRadians(sunAppLongDeg)))
        )

        val grnwchHrAngle = (279.46061 + 360.98565 * (julianDay - 2451545.5) + longitude + sunRtAscDeg - 15 * decimalHours) % 360
        val localHourAngle = (grnwchHrAngle - longitude) % 360

        val latRad = Math.toRadians(latitude)
        val sunDecRad = Math.toRadians(sunDeclin)
        val hourAngleRad = Math.toRadians(localHourAngle)

        val zenithAngle = Math.toDegrees(
            acos(
                sin(latRad) * sin(sunDecRad) +
                        cos(latRad) * cos(sunDecRad) * cos(hourAngleRad)
            )
        )

        val elevation = 90 - zenithAngle
        val azimuth = Math.toDegrees(
            atan2(
                sin(hourAngleRad),
                cos(hourAngleRad) * sin(latRad) - tan(sunDecRad) * cos(latRad)
            )
        )

        return SunPosition(
            azimuth = ((azimuth + 360) % 360).toFloat(),
            altitude = elevation.toFloat()
        )
    }

    fun calculateSunTimes(
        latitude: Double,
        longitude: Double,
        date: LocalDateTime = LocalDateTime.now()
    ): SunTimes {
        val jd = toJulianDay(date.atZone(ZoneId.systemDefault()))
        val n = jd - 2451545.0 + 0.0008

        val j2000 = 2451545.0
        val nstar = n - longitude / 360.0
        val m = (357.5291 + 0.98560028 * nstar) % 360
        val c = (1.9146 - 0.004817 * nstar - 0.000014 * nstar * nstar) * sin(Math.toRadians(m)) +
                (0.019993 - 0.000101 * nstar) * sin(Math.toRadians(2 * m)) +
                0.00029 * sin(Math.toRadians(3 * m))
        val lambda = (280.4664 + 0.98565028 * nstar + c) % 360
        val nu = nstar + 0.0053 * sin(Math.toRadians(m)) - 0.0069 * sin(Math.toRadians(2 * lambda))
        val jnoon = j2000 + nu

        val delta = Math.toDegrees(
            asin(sin(Math.toRadians(23.44)) * sin(Math.toRadians(lambda)))
        )

        val cosH = -tan(Math.toRadians(latitude)) * tan(Math.toRadians(delta))
        val h = Math.toDegrees(acos(cosH.coerceIn(-1.0, 1.0)))

        val jrise = jnoon - h / 360.0
        val jset = jnoon + h / 360.0

        return SunTimes(
            sunrise = julianDayToLocalDateTime(jrise),
            sunset = julianDayToLocalDateTime(jset)
        )
    }

    private fun toJulianDay(zdt: ZonedDateTime): Double {
        val year = zdt.year
        val month = zdt.monthValue
        val day = zdt.dayOfMonth.toDouble() + zdt.hour / 24.0 + zdt.minute / 1440.0 + zdt.second / 86400.0

        val a = (14 - month) / 12
        val y = year + 4800 - a
        val m = month + 12 * a - 3

        return day + (153 * m + 2) / 5 + 365 * y + y / 4 - y / 100 + y / 400 - 32045.5
    }

    private fun julianDayToLocalDateTime(jd: Double): LocalDateTime {
        val z = jd.toLong() + 1
        val f = jd - z.toDouble() + 1.0
        val a = if (z < 2299161) z else {
            val alpha = ((z - 1867216.25) / 36524.25).toLong()
            z + 1 + alpha - alpha / 4
        }
        val b = a + 1524
        val c = ((b - 122.1) / 365.25).toLong()
        val d = (365.25 * c).toLong()
        val e = ((b - d) / 30.6001).toLong()

        val day = (b - d - (30.6001 * e).toLong()).toInt()
        val month = if (e < 14) (e - 1).toInt() else (e - 13).toInt()
        val year = if (month > 2) (c - 4716).toInt() else (c - 4715).toInt()

        val hours = (f * 24).toInt()
        val minutes = ((f * 24 - hours) * 60).toInt()
        val seconds = (((f * 24 - hours) * 60 - minutes) * 60).toInt()

        return LocalDateTime.of(year, month, day, hours, minutes, seconds)
    }
}
