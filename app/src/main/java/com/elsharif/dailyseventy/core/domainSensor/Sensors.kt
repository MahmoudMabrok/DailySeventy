package com.elsharif.dailyseventy.core.domainSensor

import android.content.Context
import android.content.pm.PackageManager
import android.hardware.Sensor
import com.elsharif.dailyseventy.core.domainSensor.AndroidSensor

/**
 * Light sensor implementation.
 * @param context The context in which the sensor operates.
 */
class LightSensor(
    context: Context
): AndroidSensor(
    context = context,
    sensorFeature = PackageManager.FEATURE_SENSOR_LIGHT,
    sensorType = Sensor.TYPE_LIGHT
)

/**
 * Accelerometer sensor implementation.
 * @param context The context in which the sensor operates.
 */
class AccelerometerSensor(
    context: Context
): AndroidSensor(
    context = context,
    sensorFeature = PackageManager.FEATURE_SENSOR_ACCELEROMETER,
    sensorType = Sensor.TYPE_ACCELEROMETER
)

/**
 * Proximity sensor implementation.
 * @param context The context in which the sensor operates.
 */
class ProximitySensor(
    context: Context
): AndroidSensor(
    context = context,
    sensorFeature = PackageManager.FEATURE_SENSOR_PROXIMITY,
    sensorType = Sensor.TYPE_PROXIMITY
)

class MagnetometerSensor(
    context: Context
) : AndroidSensor(
    context = context,
    sensorFeature = PackageManager.FEATURE_SENSOR_COMPASS, // Optional, or use FEATURE_SENSOR_MAGNETIC_FIELD
    sensorType = Sensor.TYPE_MAGNETIC_FIELD
)