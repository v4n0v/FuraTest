package com.ezmobdev.furatest.activities

import android.arch.lifecycle.Observer
import android.os.Bundle
import com.ezmobdev.furatest.App
import com.ezmobdev.furatest.R
import com.ezmobdev.furatest.databinding.ActivityMapsBinding
import com.ezmobdev.furatest.viewModels.MainViewModel
import com.google.android.gms.maps.CameraUpdateFactory


class MapsActivity : BaseMapActivity<ActivityMapsBinding, MainViewModel>(R.layout.activity_maps) {

    override fun onCreateComplete(savedInstanceState: Bundle?) {
        mapView = binding.mapView
        initMap(savedInstanceState)

        viewModel = initViewModel()
        binding.viewModel = viewModel
        App.instance.getAppComponent().inject(binding.viewModel!!)


        viewModel.loadData()
        viewModel.furaPointersData.observe(this,
            Observer { markers ->
                if (mMap!=null && markers!=null){
                    for (point in markers.data)
                        mMap?.addMarker(point.getMarker())
                    mMap?.animateCamera(CameraUpdateFactory.newLatLngZoom(markers.getLast()?.getLatLng(), 7.0f))
                }
            })
    }

}
