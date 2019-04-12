package com.ezmobdev.furatest.activities

import android.annotation.SuppressLint
import android.arch.lifecycle.ViewModel
import android.arch.lifecycle.ViewModelProviders
import android.content.Context
import android.content.pm.PackageManager
import android.databinding.DataBindingUtil
import android.databinding.ViewDataBinding
import android.os.Bundle
import android.support.v4.app.ActivityCompat
import android.support.v4.content.ContextCompat
import android.support.v7.app.AlertDialog
import android.support.v7.app.AppCompatActivity
import com.ezmobdev.furatest.models.FuraPoint
import com.google.android.gms.maps.GoogleMap
import com.google.android.gms.maps.MapView
import com.google.android.gms.maps.OnMapReadyCallback
import com.google.android.gms.maps.model.BitmapDescriptorFactory
import com.google.android.gms.maps.model.LatLng
import com.google.android.gms.maps.model.MarkerOptions

@SuppressLint("Registered")
open class BaseMapActivity<B : ViewDataBinding, VM : ViewModel>(private val id: Int) : AppCompatActivity(),
    OnMapReadyCallback {

    companion object {
        private const val PERMISSION_FOR_ALL_REQUEST_CODE = 1654
    }
    protected var mMap: GoogleMap? = null
    protected var mapView: MapView? = null
    protected lateinit var binding: B
    protected lateinit var viewModel: VM

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        requestPermissions()
        try {
            packageManager.getPackageInfo("com.google.android.gms", 0)
            binding = DataBindingUtil.setContentView(this, id)
            onCreateComplete(savedInstanceState)
        } catch (ne: PackageManager.NameNotFoundException) {
            AlertDialog.Builder(this)
                .setTitle("Ошибка!")
                .setMessage("На устройстве не установлены Google Services")
                .setCancelable(true)
                .setPositiveButton("ОК") { _, _ ->
                    finish()
                }
                .create()
                .show()
        }
    }

    open fun onCreateComplete(savedInstanceState: Bundle?) {}

    protected fun initMap(savedInstanceState: Bundle?) {
        mapView?.onCreate(savedInstanceState)
        mapView?.getMapAsync(this)
    }

    override fun onMapReady(map: GoogleMap?) {
        mMap = map
    }

    private fun requestPermissions() {
        val requiredPermissionsFromManifest = getNotRequestedPermissions(this@BaseMapActivity)
        for (s in requiredPermissionsFromManifest) {
            if (ContextCompat.checkSelfPermission(this, s) != PackageManager.PERMISSION_GRANTED) {

                ActivityCompat.requestPermissions(
                    this@BaseMapActivity,
                    requiredPermissionsFromManifest.toTypedArray(),
                    PERMISSION_FOR_ALL_REQUEST_CODE
                )
            }
            return
        }
    }

    protected inline fun <reified VM : ViewModel> initViewModel(): VM {
        return ViewModelProviders.of(this).get(VM::class.java)
    }


    public override fun onResume() {
        mapView?.onResume()
        super.onResume()
    }


    public override fun onPause() {
        super.onPause()
        mapView?.onPause()
    }

    public override fun onDestroy() {
        super.onDestroy()
        mapView?.onDestroy()
    }

    override fun onLowMemory() {
        super.onLowMemory()
        mapView?.onLowMemory()
    }

    protected fun FuraPoint.getMarker(): MarkerOptions {
        return MarkerOptions()
            .position(LatLng(this.lat, this.lng))
            .title(this.name)
            .icon(BitmapDescriptorFactory.defaultMarker(this.getColorByType()))
    }

    protected fun FuraPoint.getLatLng(): LatLng {
        return LatLng(this.lat, this.lng)
    }

    private fun FuraPoint.getColorByType(): Float {
        return when (this.categoryId) {
            1 -> BitmapDescriptorFactory.HUE_BLUE
            else -> BitmapDescriptorFactory.HUE_RED
        }
    }

    fun getNotRequestedPermissions(context: Context): List<String> {
        with(context) {
            val list = mutableListOf<String>()
            val requiredPermissionsFromManifest =
                packageManager.getPackageInfo(packageName, PackageManager.GET_PERMISSIONS).requestedPermissions
            if (requiredPermissionsFromManifest != null)
                for (s in requiredPermissionsFromManifest) {
                    if (ContextCompat.checkSelfPermission(this, s) != PackageManager.PERMISSION_GRANTED) {
                        list.add(s)
                    }
                }
            return list
        }
    }

}