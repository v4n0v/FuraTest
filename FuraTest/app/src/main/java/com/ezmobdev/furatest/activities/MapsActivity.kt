package com.ezmobdev.furatest.activities

import android.arch.lifecycle.Observer
import android.os.Bundle
import com.ezmobdev.furatest.App
import com.ezmobdev.furatest.R
import com.ezmobdev.furatest.databinding.ActivityMapsBinding
import com.ezmobdev.furatest.viewModels.MainViewModel
import com.google.android.gms.maps.CameraUpdateFactory
import com.google.android.gms.maps.model.LatLng
import com.google.android.gms.maps.model.MarkerOptions


class MapsActivity : BaseMapActivity<ActivityMapsBinding, MainViewModel>(R.layout.activity_maps) {

    override fun onCreateComplete(savedInstanceState: Bundle?) {
        mapView = binding.mapView
        initMap(savedInstanceState)
        viewModel = initViewModel()
        App.instance.getAppComponent().inject(viewModel)
        viewModel.loadLocation()
        binding.viewModel = viewModel


        viewModel.myLocData.observe(this,
            Observer{
                it?.let {latLng->
                    val loc = LatLng(latLng.latitude, latLng.longitude)
                    mMap?.addMarker(
                        MarkerOptions()
                            .position(loc)
                            .title("Вы тут"))
                    mMap?.animateCamera(CameraUpdateFactory.newLatLngZoom(loc, 5.0f))
               }
            }
        )

        viewModel.furaPointersData.observe(this,
            Observer { markers ->
                mMap?.clear()
                if (mMap!=null && markers!=null){
                    for (point in markers.data)
                        mMap?.addMarker(point.getMarker())
                    mMap?.animateCamera(CameraUpdateFactory.newLatLngZoom(markers.getLast()?.getLatLng(), 7.0f))
                }
            })
    }

}
