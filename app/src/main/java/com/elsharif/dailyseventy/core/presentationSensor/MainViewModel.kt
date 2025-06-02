package com.elsharif.dailyseventy.core.presentationSensor

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import com.elsharif.dailyseventy.core.domainSensor.AccelerometerSensor
import com.elsharif.dailyseventy.core.domainSensor.LightSensor
import com.elsharif.dailyseventy.core.domainSensor.MagnetometerSensor
import com.elsharif.dailyseventy.core.domainSensor.ProximitySensor
import com.elsharif.dailyseventy.util.Constants.meccaLatitude
import com.elsharif.dailyseventy.util.Constants.meccaLongitude
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor(
    private val lightSensor: LightSensor,
    private val accelerometerSensor: AccelerometerSensor,
    private val proximitySensor: ProximitySensor,
    private val magnetometerSensor: MagnetometerSensor

): ViewModel() {

    var isDark by mutableStateOf(false)
    var rotationX by mutableStateOf(0f)
    var rotationY by mutableStateOf(0f)
    var sensorValues by  mutableStateOf(Pair(0f, 0f))
   // var sensorValues: Pair<Float, Float> by mutableStateOf(0f to 0f)

    var distance by mutableStateOf(0F)


    private var gravity: FloatArray? = null
    private var geomagnetic: FloatArray? = null
    var azimuth by mutableStateOf(0f) // in degrees (0 = North)

    init {
/*
        viewModelScope.launch {

        }*/
        lightSensor.startListening()
        lightSensor.setOnSensorValuesChangedListener { values ->
            //values is a list values[0] is X , values [1] is Y and values[2] is Z
            if (values.isNotEmpty()) {
                val lux = values[0]
                isDark = lux < 40f
            }

        }

        accelerometerSensor.startListening()
        accelerometerSensor.setOnSensorValuesChangedListener { values->
            val x= values[0]
            val y=values[1]
            sensorValues=Pair(x,y)

            gravity = values.toFloatArray()
            calculateAzimuth()
        }

        proximitySensor.startListening()
        proximitySensor.setOnSensorValuesChangedListener { values->
            distance =values[0]

        }

        magnetometerSensor.startListening()
        magnetometerSensor.setOnSensorValuesChangedListener { values ->
            geomagnetic = values.toFloatArray()
            calculateAzimuth()
        }


        }

    private fun calculateAzimuth() {
        val gravity = gravity
        val geomagnetic = geomagnetic

        if (gravity != null && geomagnetic != null) {
            val R = FloatArray(9)
            val I = FloatArray(9)

            val success = android.hardware.SensorManager.getRotationMatrix(R, I, gravity, geomagnetic)
            if (success) {
                val orientation = FloatArray(3)
                android.hardware.SensorManager.getOrientation(R, orientation)
                val azimuthRadians = orientation[0]
                val azimuthDegrees = Math.toDegrees(azimuthRadians.toDouble()).toFloat()
                azimuth = (azimuthDegrees + 360) % 360 // Normalize
            }
        }
    }

    fun calculateQiblaDirection(currentLat: Double, currentLon: Double): Float {
        val kaabaLat = Math.toRadians(meccaLatitude)
        val kaabaLon = Math.toRadians(meccaLongitude)
        val userLat = Math.toRadians(currentLat)
        val userLon = Math.toRadians(currentLon)

        val deltaLon = kaabaLon - userLon

        val x = Math.sin(deltaLon)
        val y = Math.cos(userLat) * Math.tan(kaabaLat) - Math.sin(userLat) * Math.cos(deltaLon)

        val bearing = Math.toDegrees(Math.atan2(x, y))

        return ((bearing + 360) % 360).toFloat() // Normalize to 0-360
    }


}