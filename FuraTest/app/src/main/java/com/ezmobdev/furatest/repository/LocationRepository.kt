package com.ezmobdev.furatest.repository

import android.annotation.SuppressLint
import android.content.Context
import android.location.Location
import android.location.LocationListener
import android.location.LocationManager
import android.os.Bundle
import android.util.Log
import com.ezmobdev.furatest.App
import io.reactivex.Observable

class LocationRepository() : LocationRepoListener(), ILocalRepo {
      var imHere: Location? = null

    override fun onLocationUpdated(location: Location?) {
        imHere = location
    }

    @SuppressLint("MissingPermission")
    override fun getLocation(): Observable<Location> {
        return Observable.fromCallable<Location> {
            val locationManager = App.instance.getSystemService(Context.LOCATION_SERVICE) as LocationManager
            val locationListener = this

            locationManager.requestLocationUpdates(
                LocationManager.GPS_PROVIDER,
                5000,
                10f,
                locationListener
            )
            locationManager.getLastKnownLocation(LocationManager.GPS_PROVIDER)

        }
    }

}

abstract class LocationRepoListener : LocationListener {
    abstract fun onLocationUpdated(location: Location?)

    override fun onLocationChanged(location: Location?) {
        onLocationUpdated(location)
    }

    override fun onStatusChanged(provider: String?, status: Int, extras: Bundle?) {}
    override fun onProviderEnabled(provider: String?) {}
    override fun onProviderDisabled(provider: String?) {}
}