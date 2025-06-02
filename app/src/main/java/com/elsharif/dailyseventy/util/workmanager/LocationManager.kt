package com.elsharif.dailyseventy.util.workmanager

import android.annotation.SuppressLint
import android.content.Context
import android.location.Location
import android.os.Looper
import com.google.android.gms.location.LocationCallback
import com.google.android.gms.location.LocationRequest
import com.google.android.gms.location.LocationResult
import com.google.android.gms.location.LocationServices
import kotlinx.coroutines.channels.awaitClose
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.callbackFlow
import kotlinx.coroutines.launch

@SuppressLint("MissingPermission") // Make sure to handle permission before calling
class LocationManager(
    context: Context
) {

    private val fusedLocationClient =
        LocationServices.getFusedLocationProviderClient(context)

    // Get the last known location once
    fun getLocation(
        onSuccess: (latitude: String, longitude: String) -> Unit
    ) {
        fusedLocationClient
            .lastLocation
            .addOnSuccessListener { location ->
                location?.let {
                    val latitude = it.latitude.toString()
                    val longitude = it.longitude.toString()

                    onSuccess(latitude, longitude)
                }
            }
    }

    // Track location continuously as a Flow<Location>
    fun trackLocation(): Flow<Location> = callbackFlow {
        val locationCallback = object : LocationCallback() {
            override fun onLocationResult(result: LocationResult) {
                super.onLocationResult(result)
                result.locations.lastOrNull()?.let { location ->
                    launch {
                        send(location)
                    }
                }
            }
        }

        val request = LocationRequest.Builder(1000) // Update every 1 second
            .build()

        fusedLocationClient.requestLocationUpdates(
            request,
            locationCallback,
            Looper.getMainLooper()
        )

        awaitClose {
            fusedLocationClient.removeLocationUpdates(locationCallback)
        }
    }
}
