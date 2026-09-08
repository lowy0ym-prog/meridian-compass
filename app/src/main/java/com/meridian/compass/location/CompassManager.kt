package com.meridian.compass.location

import android.content.Context
import android.hardware.Sensor
import android.hardware.SensorEvent
import android.hardware.SensorEventListener
import android.hardware.SensorManager
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlin.math.abs

class CompassManager(context: Context) : SensorEventListener {
    private val sensorManager = context.getSystemService(Context.SENSOR_SERVICE) as SensorManager
    private val accelerometer = sensorManager.getDefaultSensor(Sensor.TYPE_ACCELEROMETER)
    private val magnetometer = sensorManager.getDefaultSensor(Sensor.TYPE_MAGNETIC_FIELD)
    private val gyroscope = sensorManager.getDefaultSensor(Sensor.TYPE_GYROSCOPE)

    private val _heading = MutableStateFlow(0f)
    val heading: StateFlow<Float> = _heading

    private val _magneticHeading = MutableStateFlow(0f)
    val magneticHeading: StateFlow<Float> = _magneticHeading

    private val _inclination = MutableStateFlow(0f)
    val inclination: StateFlow<Float> = _inclination

    private val accelerometerReading = FloatArray(3)
    private val magnetometerReading = FloatArray(3)
    private val rotationMatrix = FloatArray(9)
    private val orientationAngles = FloatArray(3)

    private var lastHeading = 0f
    private val headingThreshold = 0.5f // degrees

    fun start() {
        sensorManager.registerListener(this, accelerometer, SensorManager.SENSOR_DELAY_NORMAL)
        sensorManager.registerListener(this, magnetometer, SensorManager.SENSOR_DELAY_NORMAL)
    }

    fun stop() {
        sensorManager.unregisterListener(this)
    }

    override fun onSensorChanged(event: SensorEvent?) {
        if (event == null) return

        when (event.sensor.type) {
            Sensor.TYPE_ACCELEROMETER -> {
                System.arraycopy(event.values, 0, accelerometerReading, 0, 3)
            }
            Sensor.TYPE_MAGNETIC_FIELD -> {
                System.arraycopy(event.values, 0, magnetometerReading, 0, 3)
            }
        }

        SensorManager.getRotationMatrix(
            rotationMatrix,
            FloatArray(9),
            accelerometerReading,
            magnetometerReading
        )

        SensorManager.getOrientation(rotationMatrix, orientationAngles)

        val heading = Math.toDegrees(orientationAngles[0].toDouble()).toFloat()
        val rawHeading = (heading + 360) % 360

        if (abs(rawHeading - lastHeading) > headingThreshold) {
            _magneticHeading.value = rawHeading
            lastHeading = rawHeading
        }

        _inclination.value = Math.toDegrees(orientationAngles[1].toDouble()).toFloat()
    }

    override fun onAccuracyChanged(sensor: Sensor?, accuracy: Int) {}
}
